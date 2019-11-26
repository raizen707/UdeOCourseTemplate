package com.example.ui.internal

import java.io.IOException
import java.lang.Exception

//Wrapper de IOException
class NoConnectivityException : IOException()
class LocationPermissionNotGrantedException : Exception()
class DataNotFoundException : Exception()