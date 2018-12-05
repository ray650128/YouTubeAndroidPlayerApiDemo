package com.ray650128.youtubeplayerdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment

class MainActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener {

    // YouTube 元件
    private lateinit var playerFragment: YouTubePlayerFragment
    private var youTubePlayer: YouTubePlayer? = null

    // YouTube 影片 ID
    private val videoId = "Hu1FkdAOws0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化 YouTube Player Fragment
        initYouTubeView()
    }

    private fun initYouTubeView() {
        playerFragment = fragmentManager.findFragmentById(R.id.youTubeFragment) as YouTubePlayerFragment
        playerFragment.initialize(API_KEY, this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        youTubePlayer = player
        if (!wasRestored) {
            youTubePlayer?.cueVideo(videoId)
        }
        youTubePlayer?.setPlayerStateChangeListener(this)
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        youTubePlayer = null
        Toast.makeText(this@MainActivity, "發生錯誤：$result", Toast.LENGTH_SHORT).show()
    }

    override fun onAdStarted() {}

    override fun onLoading() {}

    override fun onVideoStarted() {}

    override fun onLoaded(p0: String?) {
        // 當影片載入後自動播放
        youTubePlayer?.play()
    }

    override fun onVideoEnded() {}

    override fun onError(result: YouTubePlayer.ErrorReason?) {
        Toast.makeText(this@MainActivity, "發生錯誤：$result", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val API_KEY = "AIzaSyD_Sb1JPV3YhKKUEt_Cr2KxziYjA_Sz57I"
    }
}
