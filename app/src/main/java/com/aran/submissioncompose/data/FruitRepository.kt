package com.aran.submissioncompose.data

import com.aran.submissioncompose.model.Fruit
import com.aran.submissioncompose.model.FruitsData
import com.aran.submissioncompose.model.FruitsData.fruit
import com.aran.submissioncompose.model.OrderFruit
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FruitRepository {

    private val orderFruit = mutableListOf<OrderFruit>()

    init {
        if (orderFruit.isEmpty()) {
            FruitsData.fruit.forEach {
                orderFruit.add(OrderFruit(it, 0))
            }
        }
    }

    fun getAllFruits(): Flow<List<OrderFruit>> {
        return flowOf(orderFruit)
    }

    fun getOrderFruitById(rewardId: Long): OrderFruit {
        return orderFruit.first {
            it.fruit.id == rewardId
        }
    }

    fun updatedOrderFruit(fruitId: Long, newCountValue: Int) : Flow<Boolean> {
        val index = orderFruit.indexOfFirst { it.fruit.id == fruitId }
        val results = if (index >= 0) {
            val orderFruits = orderFruit[index]
            orderFruit[index] =
                orderFruits.copy(fruit = orderFruits.fruit, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(results)
    }

    fun getAddedOrderFruits() : Flow<List<OrderFruit>> {
        return getAllFruits()
            .map { orderFruit ->
                orderFruit.filter { orderFruits ->
                    orderFruits.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: FruitRepository? = null

        fun getInstance():FruitRepository =
            instance ?: synchronized(this) {
                FruitRepository().apply {
                    instance = this
                }
            }
    }
}