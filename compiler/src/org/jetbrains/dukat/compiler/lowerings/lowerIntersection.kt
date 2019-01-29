package org.jetbrains.dukat.compiler.lowerings

import org.jetbrains.dukat.ast.model.declaration.DocumentRootDeclaration
import org.jetbrains.dukat.ast.model.declaration.types.ParameterValueDeclaration
import org.jetbrains.dukat.compiler.asIntersection

private class LowerIntersection : ParameterValueLowering {
    override fun lowerParameterValue(declaration: ParameterValueDeclaration): ParameterValueDeclaration {
        declaration.asIntersection()?.let {
            val firstIntersectionType = it.params[0]
            firstIntersectionType.meta = it.copy()
            return lowerParameterValue(firstIntersectionType)
        }

        return super.lowerParameterValue(declaration)
    }
}

fun DocumentRootDeclaration.lowerIntersectionType(): DocumentRootDeclaration {
    return LowerIntersection().lowerDocumentRoot(this)
}