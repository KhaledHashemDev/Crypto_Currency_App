package com.example.crypto_currency_app.domain.use_case.get_coin

import com.example.crypto_currency_app.data.remote.dto.toCoinDetail
import com.example.crypto_currency_app.domain.model.CoinDetail
import com.example.crypto_currency_app.domain.repository.CoinRepository
import com.example.crypto_currency_app.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject



/**
 * inject constructor with repository (not repository impl)
 */
class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    // overwriting the operator fun invoke allows us to call the use case
    //GetCoinsUseCase as if it was a function, and we return a flow because
    // we want to emit states LOADING -> for progress bar, SUCCESS -> attach list of coins,
    // and ERROR
    operator fun invoke(coinId: String): kotlinx.coroutines.flow.Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.LOADING())
            //we mapped it to toCoin because we returning a list of coin, not coinDTO
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.SUCCESS<CoinDetail>(coin))

        }catch (e: HttpException){
            emit(Resource.ERROR<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.ERROR<CoinDetail>("Couldn't reach server. Check connection"))

        }
    }
}