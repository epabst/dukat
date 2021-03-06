package org.jetbrains.dukat.tsLowerings

import org.jetbrains.dukat.astCommon.NameEntity
import org.jetbrains.dukat.ownerContext.NodeOwner
import org.jetbrains.dukat.tsmodel.ParameterOwnerDeclaration
import org.jetbrains.dukat.tsmodel.TypeAliasDeclaration
import org.jetbrains.dukat.tsmodel.types.ParameterValueDeclaration
import org.jetbrains.dukat.tsmodel.types.TypeDeclaration
import org.jetbrains.dukat.tsmodel.types.TypeParamReferenceDeclaration

private class TypeSpecifierLowering(private val aliasParamMap: Map<NameEntity, ParameterValueDeclaration>) : DeclarationLowering {

    private fun spec(declaration: ParameterValueDeclaration): ParameterValueDeclaration {
        val declarationResolved = when (declaration) {
            is TypeParamReferenceDeclaration -> aliasParamMap.getOrDefault(declaration.value, declaration)
            is TypeDeclaration -> aliasParamMap.getOrDefault(declaration.value, declaration)
            else -> declaration
        }

        if (declarationResolved != declaration) {
            declarationResolved.meta = declaration.meta
        }

        return declarationResolved
    }

    override fun lowerParameterValue(declaration: ParameterValueDeclaration, owner: NodeOwner<ParameterOwnerDeclaration>?): ParameterValueDeclaration {
        return spec(super.lowerParameterValue(declaration, owner))
    }
}

class TypeAliasContext {

    private val myTypeAliasMap: MutableMap<String, TypeAliasDeclaration> = mutableMapOf()

    fun registerTypeAlias(typeAlias: TypeAliasDeclaration) {
        // TODO: this is our way of avoiding SOE on assigning meta
        myTypeAliasMap[typeAlias.uid] = typeAlias
    }
    
    fun dereference(declaration: ParameterValueDeclaration): ParameterValueDeclaration {
        return when (declaration) {
            is TypeDeclaration ->
                myTypeAliasMap[declaration.reference?.uid]?.let { aliasResolved ->
                    val aliasParamsMap: Map<NameEntity, ParameterValueDeclaration> = aliasResolved.typeParameters.zip(declaration.params).associateBy({ it.first.name }, { it.second })
                    TypeSpecifierLowering(aliasParamsMap).lowerParameterValue(aliasResolved.typeReference, NodeOwner(declaration, null))
                } ?: declaration
            else -> declaration
        }
    }

}