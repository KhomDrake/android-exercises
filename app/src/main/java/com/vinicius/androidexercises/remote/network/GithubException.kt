package com.vinicius.androidexercises.remote.network

import java.io.IOException

abstract class GithubException() : IOException()

class InvalidQException : GithubException()

class PageNumberTooLarge : GithubException()

class DefaultException : GithubException()