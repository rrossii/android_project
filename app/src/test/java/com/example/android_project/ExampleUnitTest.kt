package com.example.android_project

import org.junit.Test
import org.junit.Assert.*


class ExampleUnitTest {
    @Test
    fun adding() {
        assertEquals(4, 2+2)
    }
}

//@RunWith(AndroidJUnit4::class)
//class WfiNetworkDaoTest {
//    private lateinit var wifiNetworkDao: WifiNetworkDao
//    private lateinit var db: WifiNetworkDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//
//        db = Room.databaseBuilder(
//            context,
//            WifiNetworkDatabase::class.java,
//            "wifi_network_database"
//        ).allowMainThreadQueries().build()
//        wifiNetworkDao = db.dao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertNetwork() = runBlocking {
//        val network = WifiNetwork("wifi34", "1234", false)
//        wifiNetworkDao.insertNetwork(network)
//
//        val allNetworksFlow = wifiNetworkDao.getAllNetworks()
//        val allNetworks = allNetworksFlow.first()
//
//        assert(allNetworks.contains(network))
//    }
//}

