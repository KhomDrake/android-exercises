package com.vinicius.androidexercises.ui.search

import com.vinicius.androidexercises.utils.rule.IntentsRule
import com.vinicius.androidexercises.utils.rule.KoinRule
import org.junit.Rule
import org.junit.Test

class SearchFragmentTest {

    @get:Rule
    val koinRule = KoinRule()

    @get:Rule
    val intentsRule = IntentsRule()

    @Test
    fun openScreen_shouldDisplayedInitialState() {
        setup {

        } check {
            initialStateDisplayed()
        }
    }

    @Test
    fun openScreen_andTypeUser_shouldDisplayedUserTextAndIconToSearch() {
        setup {

        } launch {
            typeUser()
        } check {
            displayedUserTextAndIconToSearch()
        }
    }

    @Test
    fun openScreen_andTypeUser_andClickIconToSearch_shouldLoadUsersAndDisplayedUsers() {
        setup {
            withLoadUsersSuccess()
        } launch {
            typeUser()
            clickSearchIcon()
        } check {
            usersDisplayed()
            loadUsersCalled()
        }
    }

    @Test
    fun openScreenWithLoadUsersError_andTypeUser_andClickIconToSearch_shouldShowFirstPageErrorState() {
        setup {
            withLoadUsersFailure()
        } launch {
            typeUser()
            clickSearchIcon()
        } check {
            errorStateDisplayed()
        }
    }

    @Test
    fun openScreenWithLoadUsersError_andTypeUser_andClickIconToSearch_andClickTryAgain_shouldLoadUsersAgain() {
        setup {
            withLoadUsersFailure()
        } launch {
            typeUser()
            clickSearchIcon()
            clickTryAgain()
        } check {
            loadUsersCalledTwoTimes()
        }
    }

    @Test
    fun openScreen_andTypeUser_andClickIconToSearch_andScrollToLastItem_shouldLoadSecondPage() {
        setup {
            withLoadUsersTwoPages()
        } launch {
            typeUser()
            clickSearchIcon()
            scrollToLastItem()
        } check {
            loadUsersSecondPageCalled()
        }
    }

    @Test
    fun openScreen_andTypeUser_andClickIconToSearch_andClickAccessUserHomeLink_shouldOpenAccessUserHomeLink() {
        setup {
            withLoadUsersSuccess()
        } launch {
            typeUser()
            clickSearchIcon()
            clickAccessUserHomeLink()
        } check {
            openAccessUserHomeLink()
        }
    }

}