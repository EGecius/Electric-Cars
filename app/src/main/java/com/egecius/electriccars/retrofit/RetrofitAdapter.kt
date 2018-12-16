package com.egecius.electriccars.retrofit

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RetrofitAdapter {

    private val basUrl = "https://public.boxcloud.com/d/1/b1!swbZnhmt834vmwMWU5HzBFbq7uRywLPAcCNMmiwVt4gw9eNiqsRVQJ46dEDxFOmH7h23mK-rW1NG3dePjx4tRhaFkuznoyI5SdtRqZnCdBZuPYAt7XF-tFdEn9o30A-GFPtSN-Nc2NvtM9G97Hzbj1riCMUBCe_lD3TPfskYwfhtnqK4v9yWYCSu9g9BbiJi-Lbhcfyid_43BfzmjiV5i5ZTWOiUR7WIs7EtVF7k-fe_O9HfrfaqmFPDXhXp-56x-1sYmeqnh2RGicDyPkqB6J0LoqBpC6AY-ymbxz9yGNJsCk_8bPKto78ZOikxZNwbHB3V39QyqQrt75IjHC4SSAZt-Ozuaguto2wMB1wVVQYIBc6X_S8wqwJ40knP7AgNZ91odb4m-TblTxbn7zNDXLtXz-FFyWxRn8TVhzUtKMLurHY-OW_I1zkKPp8MoppgsasQg85Oyg6gRpcf_pbS-LcDuVGB-skltqhoEkAsyKI-yET_HIgOcfIiq-oMwvxwJa6CyQyoGHZk90jDa26gOE-IcstEjBuFgDL-4vIoL-XNn2X-iGOn0wRS2fb_wGR2Zr-H9vnZFB_W_hSqnT4Zn6ARDRbl8iHCG9aQiJnrNGQUy3wFtgAU0YH57oEGXuRZjDqoPNBkKAlerQoLpN88z3k2RFh4Qbh0SHrJTjP0yOYV4S6Zd30rlHQCaydIJC1pgPvRB18b8vT_kqb8i2K5N7Z2DDlPwe76phW1uOwfeRP95f1BgHepuL_0S1Uvw9pm_K_ekrtYsSUa2z0vG-aadxKGF_DTCdmqNW6LONMFNUlwEFXiBRAM3D7302gglsWq5TTYMEA49d5B7X_K5eRCVM88aCvQ7Z98LubjR8Pl1G_4B1mUK7R_1-qwryaNfWexbiG9Q9Jju9lezlxEVfFTcoSH15gAZuWX06prMf4iIcdqEl1Q48FAIITKCV5krQVsfh4eFOIOYfooFY51j93U4Q-aGzZk7J_xG4yUkDZRJA4pMA9kg4XyvfW-dpYOVJimIUqDaxJ9L3dzOx8odDiGqV-ghmeJdRaFNDUBg4XJuzWr6HjMJ9DzNCKQy6qeik-0rX8xKvnkShbC_dOilL0IKJ4Ie0Ulm7axIUgY8xzmD3KV4P1ce6OOEmWTCohI3HLx8f6IAbkekGNDbQc0k4qUlFlt-PpMTehEI-kaiy2xfyA7cHknU1U5hxBb67EnQXSIearerPhGr1yVenVIQ8d3A52lcSRixsm7avVBV7mPJO_e1Ka27ng9yQLW_sqUkFI6KIRKaNeSTqaSx5j5i3c0vcbBLGGVsFnZ9wnd60udC7OLZyuUD1YbuzppH-UDm_MuB4WdGA8EY6D7f9h4gY3MYDDWpRKoo24BhIwT-irt_GsJ5JLt3s0T/"

    fun setupRetrofit(): CarsRetrofitService {
        return Retrofit.Builder()
            .baseUrl(basUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CarsRetrofitService::class.java)
    }
}

interface CarsRetrofitService {

    @GET("download")
    fun cars(): Single<List<Car>>

}