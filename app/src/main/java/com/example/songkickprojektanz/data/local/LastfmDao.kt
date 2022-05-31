package com.example.songkickprojektanz.data.local

import androidx.room.*
import com.example.songkickprojektanz.Constants.ARTIST_TABLE
import com.example.songkickprojektanz.Constants.TOPALBUM_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface LastfmDao {
    @Insert
    suspend fun insertArtist(favourite: FavouriteArtist)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTopAlbum(cast: FavouriteTopAlbum)


    @Query("SELECT * FROM $TOPALBUM_TABLE WHERE id = :id")
    fun getTopAlbums(id: Int): Flow<FavouriteTopAlbum?>

    @Query("SELECT * FROM $TOPALBUM_TABLE ORDER BY name DESC")
    fun getAllTopAlbums(): Flow<List<FavouriteTopAlbum>>

    @Query("SELECT * FROM $ARTIST_TABLE ORDER BY id DESC")
    fun getAllFavourites(): Flow<List<FavouriteArtist>>

    @Query("SELECT * FROM $TOPALBUM_TABLE ORDER BY id DESC")
    fun getFavoritesTopAlbum(): Flow<List<FavouriteTopAlbum>>

    @Query("SELECT * FROM $ARTIST_TABLE WHERE id = :id")
    fun getArtist(id: Int): Flow<FavouriteArtist?>

    @Query("SELECT favourite FROM $ARTIST_TABLE WHERE id = :id")
    fun isArtistFavourite(id: String): Flow<Boolean>

    @Query("SELECT favourite FROM $TOPALBUM_TABLE WHERE name = :id")
    fun isAlbumFavourite(id: String): Flow<Boolean>

    @Query("DELETE FROM $ARTIST_TABLE WHERE id = :id")
    suspend fun deleteArtist(id: String)

    @Query("DELETE FROM $TOPALBUM_TABLE WHERE name = :id")
    suspend fun deleteTopAlbum(id: String)

    @Query("DELETE FROM $ARTIST_TABLE")
    suspend fun deleteAllArtists()

    @Transaction
    @Query("SELECT * FROM $ARTIST_TABLE where name = :id")
    fun getArtistsTopAlbums(id: String): Flow<ArtistsTopAlbum>

}
