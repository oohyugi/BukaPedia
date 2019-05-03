package com.oohyugi.bukasempak.api.repo

import com.oohyugi.bukasempak.api.ApiClient

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
abstract class RepoImplBase {
    var apiClient = ApiClient.makeService("http://yogiputra.com/bl_api/")
}