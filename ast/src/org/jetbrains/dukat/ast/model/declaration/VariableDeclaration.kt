package org.jetbrains.dukat.ast.model.declaration

import org.jetbrains.dukat.ast.model.declaration.types.ParameterValueDeclaration
import org.jetbrains.dukat.ast.model.declaration.types.TopLevelDeclaration

data class VariableDeclaration(
        val name: String,
        val type: ParameterValueDeclaration
) : TopLevelDeclaration