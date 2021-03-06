// [test] optionalProperties.kt
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface A

external interface B

external interface C

external interface Foo {
    var propAny: Any?
        get() = definedExternally
        set(value) = definedExternally
    var propNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var propBoolean: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var propString: String?
        get() = definedExternally
        set(value) = definedExternally
    var propAliased: String? /* "boolean" | "number" | "string" */
        get() = definedExternally
        set(value) = definedExternally
    var propSingleLiteral: String? /* "a" */
        get() = definedExternally
        set(value) = definedExternally
    var propABCIntersection: A? /* A? & B? & C? */
        get() = definedExternally
        set(value) = definedExternally
    var propABC: dynamic /* A? | B? | C? */
        get() = definedExternally
        set(value) = definedExternally
}

// ------------------------------------------------------------------------------------------
// [test] optionalProperties.foo.kt
@file:JsQualifier("foo")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package foo

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external interface Bar {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
}