package com.oppabet.site.oppabet.listener

import com.oppabet.site.oppabet.listener.AllureLogger.MethodFormatter
import io.qameta.allure.Allure
import io.qameta.allure.model.Status
import io.qameta.allure.model.StepResult
import io.qameta.allure.util.ResultsUtils
import io.qameta.atlas.core.api.Listener
import io.qameta.atlas.core.api.Target
import io.qameta.atlas.core.context.TargetContext
import io.qameta.atlas.core.internal.Configuration
import io.qameta.atlas.core.util.MethodInfo
import org.hamcrest.Matcher
import java.lang.reflect.Method
import java.util.*

internal class AllureLogger : Listener {
    private val lifecycle = Allure.getLifecycle()
    private val loggableMethods: MutableMap<String, MethodFormatter>

    override fun beforeMethodCall(methodInfo: MethodInfo,
                                  configuration: Configuration) {
        getMethodFormatter(methodInfo.method).ifPresent { formatter: MethodFormatter ->
            val name = configuration.getContext(TargetContext::class.java)
                    .map { obj: TargetContext -> obj.value }
                    .map { obj: Target -> obj.name() }
                    .orElse(methodInfo.method.name)
            val args = methodInfo.args
            lifecycle.startStep(Objects.toString(methodInfo.hashCode()),
                    StepResult().setName(formatter.format(name, args)).setStatus(Status.PASSED))
        }
    }

    override fun afterMethodCall(methodInfo: MethodInfo,
                                 configuration: Configuration) {
        getMethodFormatter(methodInfo.method)
                .ifPresent { lifecycle.stopStep(Objects.toString(methodInfo.hashCode())) }
    }

    override fun onMethodReturn(methodInfo: MethodInfo?,
                                configuration: Configuration?,
                                returned: Any?) {
    }

    override fun onMethodFailure(methodInfo: MethodInfo,
                                 configuration: Configuration,
                                 throwable: Throwable) {
        getMethodFormatter(methodInfo.method).ifPresent {
            lifecycle.updateStep { stepResult: StepResult ->
                stepResult.status = ResultsUtils.getStatus(throwable).orElse(Status.BROKEN)
                stepResult.statusDetails = ResultsUtils.getStatusDetails(throwable).orElse(null)
            }
        }
    }

    private fun getMethodFormatter(method: Method): Optional<MethodFormatter> {
        return Optional.ofNullable(loggableMethods[method.name])
    }

    private fun interface MethodFormatter {
        fun format(name: String?, args: Array<Any?>?): String?
    }

    init {
        loggableMethods = HashMap()
        loggableMethods["open"] = MethodFormatter { name: String?, args: Array<Any?>? -> "Открываем страницу '${args?.get(0)}'" }
        loggableMethods["click"] = MethodFormatter { name: String?, _: Array<Any?>? -> "Кликаем на элемент '$name'" }
        loggableMethods["submit"] = MethodFormatter { name: String?, _: Array<Any?>? -> "Нажимаем на элемент '$name'" }
        loggableMethods["clear"] = MethodFormatter { name: String?, _: Array<Any?>? -> "Очищаем элемент '$name'" }
        loggableMethods["sendKeys"] = MethodFormatter { name: String?, args: Array<Any?>? ->
            val arguments = (args?.get(0) as Array<*>).contentToString();
            "Вводим в элемент '$name' значение [${arguments}]"
        }
        loggableMethods["waitUntil"] = MethodFormatter { name: String?, args: Array<Any?>? ->
            val matcher = (if (args?.get(0) is Matcher<*>) args[0] else args?.get(1)) as Matcher<*>?
            "Ждем пока элемент '$name' будет в состоянии [$matcher]"
        }
        loggableMethods["should"] = MethodFormatter { name: String?, args: Array<Any?>? ->
            val matcher = (if (args?.get(0) is Matcher<*>) args[0] else args?.get(1)) as Matcher<*>?
            "Проверяем что элемент '$name' в состоянии [$matcher]"
        }
    }
}