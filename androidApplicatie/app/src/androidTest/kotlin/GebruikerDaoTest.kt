import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teervoetpuntjesapp.data.TeervoetAppDatabase
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class GebruikerDaoTest {

    private lateinit var gebruikerDao: GebruikerDao
    private lateinit var teervoetAppDatabase: TeervoetAppDatabase

    private var gebruiker1 = GebruikerEntity(1, "thomas", "thomas@gmail.com", "qhbf", 0)
    private var gebruiker2 = GebruikerEntity(2, "emma", "emma@gmail.com", "qhbf", 0)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        teervoetAppDatabase = Room.inMemoryDatabaseBuilder(context, TeervoetAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        gebruikerDao = teervoetAppDatabase.gebruikerDao()
    }

    private suspend fun voegEenGebruikerToe() {
        gebruikerDao.insert(gebruiker1)
    }
    private suspend fun voegTweeGebruikersToe() {
        gebruikerDao.insert(gebruiker1)
        gebruikerDao.insert(gebruiker2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        teervoetAppDatabase.clearAllTables()
        teervoetAppDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertGebruikerIntoDb() = runBlocking {
        voegEenGebruikerToe()
        val allItems = gebruikerDao.getAllGebruikers().first()
        assertEquals(allItems[0], gebruiker1)
    }
    @Test
    @Throws(Exception::class)
    fun daoInsert_insertMeerdereGebruikersIntoDb() = runBlocking {
        voegTweeGebruikersToe()
        val allItems = gebruikerDao.getAllGebruikers().first()
        assertEquals(allItems[0], gebruiker1)
        assertEquals(allItems[1], gebruiker2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGet_haalGebruikerOp() = runBlocking {
        voegTweeGebruikersToe()

        val gebruiker = gebruikerDao.getGebruiker("emma@gmail.com", "qhbf").first()

        assertEquals(gebruiker2, gebruiker)
    }
}
