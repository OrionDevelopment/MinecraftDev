/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.mixin.framework

import com.demonwav.mcdev.asset.PlatformAssets
import com.demonwav.mcdev.platform.mixin.util.MixinConstants
import com.demonwav.mcdev.util.manifest
import com.intellij.framework.library.LibraryVersionProperties
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider
import com.intellij.openapi.roots.libraries.LibraryProperties
import com.intellij.openapi.vfs.VirtualFile
import java.util.jar.Attributes

class MixinPresentationProvider : LibraryPresentationProvider<LibraryVersionProperties>(MIXIN_LIBRARY_KIND) {

    override fun getIcon(properties: LibraryProperties<*>?) = PlatformAssets.MIXIN_ICON

    override fun detect(classesRoots: List<VirtualFile>): LibraryVersionProperties? {
        for (classesRoot in classesRoots) {
            val attributes = classesRoot.manifest?.mainAttributes ?: continue
            val agent = attributes.getValue(Attributes.Name("Agent-Class")) ?: continue

            if (agent != MixinConstants.Classes.MIXIN_AGENT) {
                continue
            }

            val version = attributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION) ?: continue
            return LibraryVersionProperties(version)
        }
        return null
    }
}