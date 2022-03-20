package com.vinicius.androidexercises.ui.detail.information

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import com.vinicius.androidexercises.R
import com.vinicius.androidexercises.data.ui.License
import com.vinicius.androidexercises.ui.detail.LICENSE_ARGS
import com.vinicius.androidexercises.utils.Check
import com.vinicius.androidexercises.utils.Launch
import com.vinicius.androidexercises.utils.Setup
import com.vinicius.androidexercises.utils.extension.hasText
import com.vinicius.androidexercises.utils.extension.isDisplayed
import com.vinicius.androidexercises.utils.extension.isNotDisplayed

fun LicenseFragmentTest.setup(func: LicenseFragmentSetup.() -> Unit) =
    LicenseFragmentSetup().apply(func)

class LicenseFragmentSetup : Setup<LicenseFragmentLaunch, LicenseFragmentCheck> {

    private var args = Bundle()

    override fun createCheck(): LicenseFragmentCheck {
        return LicenseFragmentCheck()
    }

    override fun createLaunch(): LicenseFragmentLaunch {
        return LicenseFragmentLaunch()
    }

    override fun setupLaunch() {
        launchFragmentInContainer<LicenseFragment>(args, themeResId = R.style.Theme_Androidexercises)
    }

    fun withArguments() {
        args.apply {
            putParcelable(LICENSE_ARGS, License(
                "", "", "Apache License 2.0"
            ))
        }
    }

    fun withoutArguments() {
        args = Bundle()
    }
}

class LicenseFragmentLaunch : Launch<LicenseFragmentCheck> {
    override fun createCheck(): LicenseFragmentCheck {
        return LicenseFragmentCheck()
    }

}

class LicenseFragmentCheck : Check {
    fun licenseDisplayed() {
        R.id.license_name.hasText("Apache License 2.0")
        R.id.license_empty.isNotDisplayed()
    }

    fun emptyStateDisplayed() {
        R.id.license_empty.apply {
            hasText("The repository don't have a license")
            isDisplayed()
        }
    }

}