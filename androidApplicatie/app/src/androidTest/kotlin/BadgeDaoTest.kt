import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.teervoetpuntjesapp.data.TeervoetAppDatabase
import com.example.teervoetpuntjesapp.data.badge.BadgeDao
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class BadgeDaoTest {

    private lateinit var badgeDao: BadgeDao
    private lateinit var teervoetAppDatabase: TeervoetAppDatabase

    private var badge1 = BadgeEntity(1, "1", "1")
    private var badge2 = BadgeEntity(2, "2", "2")
    private var badge3 = BadgeEntity(3, "3", "3")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        teervoetAppDatabase = Room.inMemoryDatabaseBuilder(context, TeervoetAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        badgeDao = teervoetAppDatabase.badgeDao()
    }

    private suspend fun voegBadgeToe() {
        badgeDao.insert(listOf(badge1))
    }

    private suspend fun voegMeerdereBadgesToe() {
        badgeDao.insert(listOf(badge1, badge3, badge2))
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        teervoetAppDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertBadgeIntoDb() = runBlocking {
        voegBadgeToe()
        val allitems = badgeDao.getAllBadges().first()
        assertEquals(allitems[0], badge1)
    }
}
