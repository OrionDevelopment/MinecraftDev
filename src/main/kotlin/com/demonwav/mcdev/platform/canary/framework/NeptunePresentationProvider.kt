/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.canary.framework

import com.demonwav.mcdev.asset.PlatformAssets
import com.demonwav.mcdev.util.manifest
import com.intellij.framework.library.LibraryVersionProperties
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider
import com.intellij.openapi.roots.libraries.LibraryProperties
import com.intellij.openapi.vfs.VirtualFile
import java.util.jar.Attributes

class NeptunePresentationProvider : LibraryPresentationProvider<LibraryVersionProperties>(NEPTUNE_LIBRARY_KIND) {

    override fun getIcon(properties: LibraryProperties<*>?) = PlatformAssets.NEPTUNE_ICON

    override fun detect(classesRoots: List<VirtualFile>): LibraryVersionProperties? {
        for (classesRoot in classesRoots) {
            val attributes = classesRoot.manifest?.mainAttributes ?: continue
            val title = attributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE) ?: continue

            if (title != "NeptuneLib") {
                continue
            }

            val version = attributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION) ?: continue
            return LibraryVersionProperties(version)
        }
        return null
    }
}