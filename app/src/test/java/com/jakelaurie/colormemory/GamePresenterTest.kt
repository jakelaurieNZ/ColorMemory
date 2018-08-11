package com.jakelaurie.colormemory

import com.jakelaurie.colormemory.ui.game.*
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GamePresenterTest {

    @Mock
    private lateinit var adapter: GameViewAdapter

    @Mock
    private lateinit var dataProvider: ResourceGameDataProvider

    lateinit var gamePresenter: GamePresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gamePresenter = GamePresenter(adapter, dataProvider,0)
    }

    @Test
    fun newGameTest() {
        var scoreSet = -1

        gamePresenter.setView(object: GameContract.View {
            override fun setAdapter(gameAdapter: GameViewAdapter) {

            }

            override fun onScoreChanged(points: Int) {
                scoreSet = points
            }

            override fun onGameCompleted(points: Int) {

            }
        })

        gamePresenter.newGame()
        Assert.assertEquals(scoreSet, 0)
    }
}