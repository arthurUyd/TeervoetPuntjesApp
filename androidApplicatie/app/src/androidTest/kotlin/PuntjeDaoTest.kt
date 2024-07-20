import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teervoetpuntjesapp.data.TeervoetAppDatabase
import com.example.teervoetpuntjesapp.data.badge.BadgeDao
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import com.example.teervoetpuntjesapp.data.puntje.PuntjeDao
import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class PuntjeDaoTest {

    private lateinit var puntjeDao: PuntjeDao
    private lateinit var teervoetAppDatabase: TeervoetAppDatabase
    private lateinit var badgeDao: BadgeDao
    private var badge1 = BadgeEntity(1, "1", "1")
    private var badge2 = BadgeEntity(2, "2", "2")
    private var puntje1 = PuntjeEntity(1, "", 1)
    private var puntje2 = PuntjeEntity(2, "", 2)
    private var puntje3 = PuntjeEntity(3, "", 2)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        teervoetAppDatabase = Room.inMemoryDatabaseBuilder(context, TeervoetAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        puntjeDao = teervoetAppDatabase.puntjeDao()
        badgeDao = teervoetAppDatabase.badgeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        teervoetAppDatabase.clearAllTables()
        teervoetAppDatabase.close()
    }

    private suspend fun voegPuntjeToe() {
        badgeDao.insert(listOf(badge1))
        puntjeDao.insert(listOf(puntje1))
    }
    private suspend fun voegMeerderePuntjesToe() {
        badgeDao.insert(listOf(badge1, badge2))
        puntjeDao.insert(listOf(puntje1, puntje2, puntje3))
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertPuntjeIntoDb() = runBlocking {
        voegPuntjeToe()
        val item = puntjeDao.getAllPuntjes().first()
        assertEquals(item[0], puntje1)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertMeerderePuntjesIntoDb() = runBlocking {
        voegMeerderePuntjesToe()
        val item = puntjeDao.getAllPuntjes().first()
        assertEquals(item[0], puntje1)
        assertEquals(item[1], puntje2)
        assertEquals(item[2], puntje3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGet_getPuntjesForBadge() = runBlocking {
        voegMeerderePuntjesToe()

        val puntjes = puntjeDao.getPuntjesVoorBadge(2).first()

        assertEquals(puntje2, puntjes[0])
        assertEquals(puntje3, puntjes[1])
    }
}
