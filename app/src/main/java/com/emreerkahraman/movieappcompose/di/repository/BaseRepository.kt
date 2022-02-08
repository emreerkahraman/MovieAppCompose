package com.emreerkahraman.movieappcompose.di.repository
import com.emreerkahraman.movieappcompose.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepository {


    fun <T> responseWrapper(remoteCall: suspend () -> Response<T>): Flow<Resource<T?>> =
        flow {
            emit(Resource.loading())
            val result = remoteCall.invoke().body()
            emit(Resource.success(result))
        }.catch { throwable ->
            emit(Resource.error(throwable))
        }


}