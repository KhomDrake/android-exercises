package com.vinicius.androidexercises.ui.detail.information

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import com.vinicius.androidexercises.R
import com.vinicius.androidexercises.ui.detail.LINKS_REPOSITORY_ARGS
import com.vinicius.androidexercises.ui.detail.LINKS_USER_ARGS
import com.vinicius.androidexercises.utils.Check
import com.vinicius.androidexercises.utils.Launch
import com.vinicius.androidexercises.utils.Setup
import com.vinicius.androidexercises.utils.extension.checkLinkOpening
import com.vinicius.androidexercises.utils.extension.click
import com.vinicius.androidexercises.utils.extension.hasText
import com.vinicius.androidexercises.utils.extension.mockLinkOpening

fun LinksFragmentTest.setup(func: LinksFragmentSetup.() -> Unit) =
    LinksFragmentSetup().apply(func)

class LinksFragmentSetup : Setup<LinksFragmentLaunch, LinksFragmentCheck> {

    private val args = Bundle()

    override fun createCheck(): LinksFragmentCheck {
        return LinksFragmentCheck()
    }

    override fun createLaunch(): LinksFragmentLaunch {
        return LinksFragmentLaunch()
    }

    override fun setupLaunch() {
        launchFragmentInContainer<LinksFragment>(args, themeResId = R.style.Theme_Androidexercises)
    }

    fun withArguments() {
        args.apply {
            putString(LINKS_USER_ARGS, "https://github.com/square")
            putString(LINKS_REPOSITORY_ARGS, "https://github.com/square/okhttp")
        }
    }
}

class LinksFragmentLaunch : Launch<LinksFragmentCheck> {

    override fun createCheck(): LinksFragmentCheck {
        return LinksFragmentCheck()
    }

    fun clickRepositoryLink() {
        "https://github.com/square/okhttp".mockLinkOpening()
        R.id.repository_link.click()
    }

    fun clickUserLink() {
        "https://github.com/square".mockLinkOpening()
        R.id.owner_link.click()
    }

}

class LinksFragmentCheck : Check {
    fun linksDisplayed() {
        R.id.repository_link.hasText("https://github.com/square/okhttp")
        R.id.owner_link.hasText("https://github.com/square")
    }

    fun openRepositoryLink() {
        "https://github.com/square/okhttp".checkLinkOpening()
    }

    fun openUserLink() {
        "https://github.com/square".checkLinkOpening()
    }
}