package com.teacherworkout.commons.ui.navigation

object AppDestinations {

    object Features {
        /**
         * The account feature
         */
        const val account = "account"

        /**
         * The home feature
         */
        const val home = "home"
    }

    object Home {
        /**
         * The landing screen for home feature
         */
        const val landing = "home-landing"
    }

    /**
     * Represent the initial screen the user sees, where he can choose to register a new account or authenticate into
     * the one he already has.
     */
    const val landing = "landing"

    /**
     * Represents the screen where the user can register a new account.
     */
    const val registration = "registration"

    /**
     * Represents the screen where the user can enter his account, once he has one.
     * From this destination the user can go the (onboarding/main) screen or the reset password screen.
     */
    const val authentication = "authentication"

    /**
     * Represents the screen where the user can reset his password.
     */
    const val reset_password = "reset_password"

    /**
     * Dialog destination which will be seen by the user once he entered valid credentials to authenticate into his
     * account.This destination will then show the user the status for the request of creating a new account:
     * in progress, successful(with the option to go to the onboarding/main screen) or failed(with the option to close
     * the dialog and try again)!
     */
    const val authentication_outcome = "authentication_outcome"

    /**
     * Dialog destination which will be seen by the user once he entered valid information to reset his password.
     * This destination will then show the user the status for the request of resetting the account: in progress,
     * successful(a email with reset link was sent by the backend, offer the option to go to authentication) or failed
     * (with the option to close the dialog and try again)!
     */
    const val reset_password_outcome = "reset_password_outcome"
}
