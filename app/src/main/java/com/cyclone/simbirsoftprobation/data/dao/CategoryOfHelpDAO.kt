package com.cyclone.simbirsoftprobation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import io.reactivex.Flowable

@Dao
interface CategoryOfHelpDAO {

    @Query("select * from category_of_help")
    fun getCategories(): Flowable<List<CategoryOfHelp>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCategories(categoryOfHelp: CategoryOfHelp)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCategories(categoriesOfHelp: List<CategoryOfHelp>)

    @Query("delete from category_of_help")
    fun deleteCategories()
}