package ste.demo.mvp.kotlin.network


import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import ste.demo.mvp.kotlin.model.ProcedureDetails
import ste.demo.mvp.kotlin.model.Procedures


interface ProcedureService {


    @GET("/procedures")
    fun getAllItems(): Observable<MutableList<Procedures>>

    @GET("/procedure_details")
    fun getItemById(@Query("id") name: String): Observable<MutableList<ProcedureDetails>>


}