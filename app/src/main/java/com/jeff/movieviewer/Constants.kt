package com.jeff.movieviewer

class Constants private constructor() {
    object Gateways {
        const val COVID19API = "https://api.covid19api.com"
        const val JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com"
        const val AMAZONAWS = "http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com"
    }
    object DaoExceptions {
        const val ERROR_EMPTY_RESULT = "Empty results[] from DAO request"
        const val ERROR_NULL_RESULT = "Null results[] from DAO request"
    }
}