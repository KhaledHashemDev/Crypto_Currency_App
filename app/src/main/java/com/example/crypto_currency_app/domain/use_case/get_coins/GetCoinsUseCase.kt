package com.example.crypto_currency_app.domain.use_case.get_coins

import com.example.crypto_currency_app.data.remote.dto.toCoin
import com.example.crypto_currency_app.domain.model.Coin
import com.example.crypto_currency_app.domain.repository.CoinRepository
import com.example.crypto_currency_app.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * inject constructor with repository (not repository impl)
 */
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    // overwriting the operator fun invoke allows us to call the use case
    //GetCoinsUseCase as if it was a function, and we return a flow because
   // we want to emit states LOADING -> for progress bar, SUCCESS -> attach list of coins,
    // and ERROR
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<ArrayList<Coin>>> = flow {
           try {
                     emit(Resource.LOADING<ArrayList<Coin>>())
               //we mapped it to toCoin because we returning a list of coin, not coinDTO
               val coins = repository.getCoins().map { it.toCoin() }
               emit(Resource.SUCCESS<ArrayList<Coin>>(coins as ArrayList<Coin>))

           }catch (e: HttpException){
               emit(Resource.ERROR<ArrayList<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
           }catch (e: IOException){
                emit(Resource.ERROR<ArrayList<Coin>>("Couldn't reach server. Check connection"))

           }
       }
    }
