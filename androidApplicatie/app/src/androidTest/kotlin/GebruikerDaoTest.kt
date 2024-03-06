import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.TeervoetAppDatabase
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerDao
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

    private var gebruiker1 = Gebruiker(1, "thomas", "thomas@gmail.com", "qhbf", 0)
    private var gebruiker2 = Gebruiker(1, "emma", "emma@gmail.com", "qhbf", 0)

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
    fun closeDb(){
        teervoetAppDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertItemIntoDb() = runBlocking {
        voegEenGebruikerToe()
        val allItems = gebruikerDao.getAllGebruikers().first()
        assertEquals(allItems[0], gebruiker1)
    }
}
