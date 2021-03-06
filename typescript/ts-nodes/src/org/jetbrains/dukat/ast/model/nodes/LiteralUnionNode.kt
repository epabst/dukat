package org.jetbrains.dukat.ast.model.nodes

import org.jetbrains.dukat.astCommon.MetaData

enum class UnionLiteralKind {
    STRING,
    NUMBER
}

data class LiteralUnionNode(
        val params: List<String>,
        val kind: UnionLiteralKind,

        override var nullable: Boolean = false,
        override var meta: MetaData? = null
): TypeNode