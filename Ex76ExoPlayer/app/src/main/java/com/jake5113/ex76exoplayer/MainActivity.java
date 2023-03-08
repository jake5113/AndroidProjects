package com.jake5113.ex76exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class MainActivity extends AppCompatActivity {

    // 비디오 재생 라이브러리 : ExoPlayer - 유튜브의 재생기술
    PlayerView playerView;
    ExoPlayer exoPlayer;

    StyledPlayerView pv;
    ExoPlayer exoPlayer2;
    // 동영상 주소
    Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        // 영상을 CD로 굽듯이.
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play(); // 자동으로 로딩완료까지 기다렸다가 재생함.

        // 플레이리스트 처럼 여러개의 영상 등록
//        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
//        Uri secondUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
//        MediaItem item1 = MediaItem.fromUri(firstUri);
//        MediaItem item2 = MediaItem.fromUri(secondUri);
//        exoPlayer.addMediaItem(item1);
//        exoPlayer.addMediaItem(item2);
//        exoPlayer.prepare();
//        exoPlayer.play();
//        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);

        // 컨트롤 박스 모양 별도 레이아웃으로 지정.
        // layout 폴더 안에 [exo_player_control_view.xml ] 로 레이아웃 만들면 바로 적용됨


        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());


        // 개선된 컨트롤바 모양을 가진 스타일드 플레이어 뷰
        pv = findViewById(R.id.pv);
        exoPlayer2 = new ExoPlayer.Builder(this).build();
        pv.setPlayer(exoPlayer2);

        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
        MediaItem item = MediaItem.fromUri(firstUri);
        exoPlayer2.setMediaItem(item);
        exoPlayer2.prepare();
//        exoPlayer2.play();
    }

    private void clickBtn() {
        // 전체화면 모드로 변경 - 별도의 전체화면용 액티비티 실행
        Intent intent = new Intent(this, FullScreenActivity.class);
        // 현재 재생중인 Uri 데이터를 전달
        intent.setData(videoUri);

        // 현재까지 재생된 위치정보도 추가로 전달
        intent.putExtra("currPos", exoPlayer.getCurrentPosition());

        startActivity(intent);

    }

    // 화면이 안보이기 시작할때 동영상 일시정지
    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }

    // 액티비티가 완전히 종료될때 플레이어 완전 제거
    @Override
    protected void onDestroy() {
        super.onDestroy();

        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }
}