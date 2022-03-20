package com.vinicius.androidexercises.ui.detail.information

import com.vinicius.androidexercises.utils.rule.IntentsRule
import org.junit.Rule
import org.junit.Test

class LinksFragmentTest {

    @get:Rule
    val intentsRule = IntentsRule()

    @Test
    fun whenOpenFragmentWithArguments_shouldShowLinks() {
        setup {
            withArguments()
        } check {
            linksDisplayed()
        }
    }

    @Test
    fun whenClickRepositoryLink_shouldOpenRepositoryLink() {
        setup {
            withArguments()
        } launch {
            clickRepositoryLink()
        } check {
            openRepositoryLink()
        }
    }

    @Test
    fun whenClickUserLink_shouldOpenUserLink() {
        setup {
            withArguments()
        } launch {
            clickUserLink()
        } check {
            openUserLink()
        }
    }

}