package com.example.genshin_dictionary.contentsList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ActivityContentListBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef:DatabaseReference
    private lateinit var binding : ActivityContentListBinding

    val bookmarkIdList = mutableListOf<String>()
    lateinit var rvAdapter: ContentRvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        rvAdapter = ContentRvAdapter(baseContext, items,itemKeyList,bookmarkIdList)

        val database = Firebase.database

        val category = intent.getStringExtra("category")

        if(category=="category_all"){
            myRef = database.getReference("contents_all")


        }else if(category=="category_newbie"){
            myRef = database.getReference("contents_newbie")

        }else if(category=="category_battle"){
            myRef = database.getReference("contents_battle")

        }else if(category=="category_quest"){
            myRef = database.getReference("contents_quest")

        }else if(category=="category_farm"){
            myRef = database.getReference("contents_farm")

        }else if(category=="category_arc"){
            myRef = database.getReference("contents_arc")

        }else if(category=="category_tip"){
            myRef = database.getReference("contents_tip")

        }else if(category=="category_etc"){
            myRef = database.getReference("contents_etc")

        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    Log.d("ContentListActivity", dataModel.toString())
                    Log.d("ContentListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        binding.rv.adapter = rvAdapter
        binding.rv.layoutManager=GridLayoutManager(this,2)
        getBookmarkData()
        
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for(dataModel in dataSnapshot.children) {
                    bookmarkIdList.add(dataModel.key.toString())

                }
                Log.d("ContentListActivity",bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }


}

//뉴비
//        myRef.push().setValue(
//            ContentModel("원신 뉴비를 위한 입문서",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fn3hIR%2FbtrfBrPmR5A%2FhDbBrtLKdkknBFHHPZ2WX1%2Fimg.png",
//                "https://secretpoten.tistory.com/413?category=967221")
//        )
//        myRef.push().setValue(
//            ContentModel("■원신 캐릭터 등급표 : 무과금 유저 추천 뉴비 필수 가이드■",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhXVIB%2Fbtrfc6TCOg3%2FemLQv9U3Kl2kx93ONKv330%2Fimg.png",
//                "https://houkawhoukaw.tistory.com/2471")
//        )
//        myRef.push().setValue(
//            ContentModel("원신 - 무과금으로 즐기는 팁",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FCXLem%2FbtroT6hsVdI%2FBOKMR5Myw8dA14FiB7jSzk%2Fimg.png",
//                "https://asecurity.dev/entry/%EC%9B%90%EC%8B%A0-%EB%AC%B4%EA%B3%BC%EA%B8%88%EC%9C%BC%EB%A1%9C-%EC%A6%90%EA%B8%B0%EB%8A%94-%ED%8C%81")
//        )
//        myRef.push().setValue(
//            ContentModel("[원신] 뉴비를 위한 원신 약어",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FWw0jt%2FbtriCE6eHGU%2FLHX18HhVObkMMusunlpN1K%2Fimg.png",
//                "https://flytothemars.tistory.com/entry/%EC%9B%90%EC%8B%A0-%EB%89%B4%EB%B9%84%EB%A5%BC-%EC%9C%84%ED%95%9C-%EC%9B%90%EC%8B%A0-%EC%95%BD%EC%96%B4")
//        )
//        myRef.push().setValue(
//            ContentModel("원신 - 초보를 위한 성유물 설명",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcjdO4V%2FbtqUwyW6QNx%2FtxmRlKkIdR7IDgotzRnKqk%2Fimg.jpg",
//                "https://identity-five.tistory.com/17")
//        )
//        myRef.push().setValue(
//            ContentModel("[원신] 초보자 육성법 / 무과금 / 무자본",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Ftj7bI%2FbtrjNc8cknz%2FKSBTaDd6QHNOQeT0KKojWK%2Fimg.png",
//                "https://flytothemars.tistory.com/entry/%EC%9B%90%EC%8B%A0-%EC%B4%88%EB%B3%B4%EC%9E%90-%EC%9C%A1%EC%84%B1%EB%B2%95-%EB%AC%B4%EA%B3%BC%EA%B8%88-%EB%AC%B4%EC%9E%90%EB%B3%B8")
//        )
//        myRef.push().setValue(
//            ContentModel("[정보] 원신 초보자가이드(무과금, 소과금)",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb3bw7a%2FbtqLFogULxW%2FZyGoFfO9vQ3c4djZpJ5km0%2Fimg.png",
//                "https://2daymakrs.tistory.com/12")
//        )
//        myRef.push().setValue(
//            ContentModel("원신 리세마라계정(스타트계정) 구매 가이드 및 주의사항[정보]",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FehujIo%2Fbtrbmzqly6Y%2FPeIjEZ59iup9pCXJWUuXzK%2Fimg.png",
//                "https://worth.tistory.com/140")
//        )
//        myRef.push().setValue(
//            ContentModel("원신 리세마라 최단루트! 리세마라 최대한 빨리 하는 법 소개",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F995CC13E5F89B6B90D",
//                "https://kikkik234.tistory.com/697")
//        )
//전투
//myRef.push().setValue(
//ContentModel(
//"원신 공략 - 조작 및 전투 기본 가이드",
//"https://static.inven.co.kr/column/2020/09/29/news/i8276301821.jpg",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=245165")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 원소 반응에 대한 유형과 종류, 원소 조합에 따른 효과",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmdrCC%2FbtqKfqvbdR2%2Fz7kNX6mCrqrjc5bcBfXppK%2Fimg.png",
//"https://travous.tistory.com/289")
//)
//myRef.push().setValue(
//ContentModel(
//"뉴비들을 위한 쉬운 원소반응 설명",
//"https://ac.namu.la/20210913s1/a3b57694396cc75e4fcda33b547f3ee61e6c1cfa2981f679c1563248f16ae953.png",
//"https://arca.live/b/genshin/33961041?p=1")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 토벌 보스 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxPaXu%2FbtqMhtg2jKU%2FOBif39vHAHHqElaKnClnJ0%2Fimg.jpg",
//"https://travous.tistory.com/337")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 영역 토벌 - 풍마룡·드발린 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxjvMK%2FbtqL8nPjNMq%2FjDMMr5kFIEvCluE5gsYiSk%2Fimg.jpg",
//"https://travous.tistory.com/332")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 영역 토벌 - 북풍의 늑대 울프의 영주 안드리우스 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdX1NvD%2FbtqMdEDCTou%2Fc84mMyYaDhKCS3MyKzE840%2Fimg.jpg",
//"https://travous.tistory.com/333")
//)
//
//myRef.push().setValue(
//ContentModel(
//"원신 공략 - 우인단 집행관 타르탈리아 (타르탈리아 레이드)",
//"https://static.inven.co.kr/column/2020/11/15/news/i14382232078.jpg",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=247164")
//)
//myRef.push().setValue(
//ContentModel(
//"[공략] 주간 속성 2개를 확인하고 입장! 야타용왕 공략",
//"https://static.inven.co.kr/column/2021/11/18/news/i13863840419.jpg",
//"https://www.inven.co.kr/webzine/news/?news=265500&site=genshin")
//)
//
//myRef.push().setValue(
//ContentModel(
//"원신(Genshin) 제2장 제2막 무념무상, 포영의절멸 - 라이덴쇼군 공략방법",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FX8MJM%2FbtraWTwbTy8%2FzUW3aslfGznDCnyymaoIu0%2Fimg.jpg",
//"https://lunavenia.tistory.com/583")
//)
//myRef.push().setValue(
//ContentModel(
//"원신(Genshin) 시뇨라 공략 추천 파티와 보상 - 나루카미섬·천수각",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQXmZt%2FbtreMCR51eL%2F4bWH0Gjc23KOTttzAPKwl1%2Fimg.jpg",
//"https://lunavenia.tistory.com/643")
//)
//퀘스트
//myRef.push().setValue(
//ContentModel(
//"원신 몬드성 내부 월드퀘스트 총정리&화려한 보물상자",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FebXRY8%2FbtqLLGWNblm%2Fd7zyL3HuTSR7AzqBLkBMvK%2Fimg.png",
//"https://sd2game.tistory.com/36")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 리월 미션 전체 리스트",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbftn7A%2FbtrbyHoHs6s%2F3khEoMEJhtwLjK3tM6yKX0%2Fimg.jpg",
//"https://flatsun.tistory.com/2485")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 이나즈마 미션 전체 리스트",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FpgeMX%2Fbtrao55IjQD%2FIkLFDUeWYtG4nZ58RIubIK%2Fimg.jpg",
//"https://flatsun.tistory.com/2413")
//)
//
//myRef.push().setValue(
//ContentModel(
//"원신 츠루미 섬 각종 퀘스트 공략 링크 모음",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmeBNP%2FbtrjzljTNsd%2FKD1Tw0sJLTFTD5qToMjq7k%2Fimg.png",
//"https://landsblog.tistory.com/171")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 스피드 도전 히든퀘스트 상자 12개 얻는 미니게임 총정리",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRUoxo%2FbtqLkgZeBsy%2FcCPDo9h87hec4AbeTQUMHK%2Fimg.jpg",
//"https://sd2game.tistory.com/24")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 히든퀘 무인도 외딴섬 가는 법, <시간과 바람> 히든퀘스트 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbgh2H0%2FbtqLElk9ZPs%2F1E5lrelH8GxCHkdnsKpvRk%2Fimg.png",
//"https://pangya.tistory.com/79")
//)
//파밍
//myRef.push().setValue(
//ContentModel(
//"원신 일일 몬스터 파밍 루트 (몬스터 위치, 재료 파밍 공략)",
//"https://static.inven.co.kr/column/2020/10/27/news/i13392627284.png",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=246242")
//)
//myRef.push().setValue(
//ContentModel(
//"원신(Genshin) 성유물을 파밍하는 방법 - 언제부터, 어떻게 해야할까?",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fr800E%2FbtreJRBcwMu%2FskMj8FLgU70mkA0gS7PZzK%2Fimg.jpg",
//"https://lunavenia.tistory.com/634")
//)
//myRef.push().setValue(
//ContentModel(
//"■원신 공략 : 파밍표 정리본 (특성 육성/무기 돌파/캐릭터 육성 소재)■",
//"https://t1.daumcdn.net/cfile/tistory/995CB7485F83AFA61A",
//"https://pinkgamesesang.tistory.com/2288")
//)
//myRef.push().setValue(
//ContentModel(
//"파밍 가이드",
//"https://ac2-p.namu.la/20210430/3de5f7690a530cd0099a11c3e1567bc6715053527dbf532f334cf03815ded12a.png",
//"https://arca.live/b/genshin/25393428")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 성유물 일일 파밍루트 13분 성유물 하루 64개 (포인트 52곳)",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcBQXyJ%2FbtqU7c7s8IP%2FT0DOzilfoZGUDB4oLC5IR1%2Fimg.jpg",
//"https://landsblog.tistory.com/15")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 돌파재료상인 NPC 위치 정리(파밍상점)",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FvYq5x%2Fbtq0oLJJTa4%2FfK0zKnx4STtaa0PHicy8X1%2Fimg.png",
//"https://anitime39.tistory.com/5")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 수정코어 파밍 루트",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fv8pCy%2FbtqXZHITuWZ%2F4tIp1rgWxmII6BaH4KxE4k%2Fimg.png",
//"https://like-tea.tistory.com/280")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 유적 기계 병사 위치 및 파밍 루트 정리",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcPDFiN%2FbtrftoeGiFH%2FWJTK39ewCp8g1i50EBJqh0%2Fimg.png",
//"https://flatsun.tistory.com/2565")
//)
//
//myRef.push().setValue(
//ContentModel(
//"원신 삼나무 위치 & 파밍 루트",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbOFgaE%2Fbtq4MSDBg94%2FLBErukJdpxykqeuLKq4iwK%2Fimg.jpg",
//"https://gamformation.tistory.com/102")
//)
//myRef.push().setValue(
//ContentModel(
//"요약 일일 파밍 루트 리스트",
//"https://ac-p.namu.la/24/240755a0139bead5b7bf5a19b9d033c7deb2ed8eacecab250b3afce8b460c8d6.jpg",
//"https://arca.live/b/genshin/9057831")
//)
//
//myRef.push().setValue(
//ContentModel(
//"■원신 공략편■ 재료 채집 루트 최적 편(리월/몬드버젼)",
//"https://t1.daumcdn.net/cfile/tistory/99C649475F890D5C1F",
//"https://baystory.tistory.com/2651")
//)
//업적
//myRef.push().setValue(
//ContentModel(
//"[업적]숨겨진 업적을 알고 싶은 사람을 위한 천지만물 업적정리",
//"https://dcimg1.dcinside.com/viewimage.php?id=22b3c32eecdc28b461b5d3b602&no=24b0d769e1d32ca73dec82fa11d02831d5ca5516da218d33b13f2460bb1b5b2a206fd035787ac1f94115ca5a80e339f771e6d2b12b87497bf3310609cfcbd3674ff4111e65c76c25&orgExt",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=1434624")
//)
//myRef.push().setValue(
//ContentModel(
//"2.1버전 신규 히든업적 정리",
//"https://dcimg1.dcinside.com/viewimage.php?id=22b3c32eecdc28b461b5d3b602&no=24b0d769e1d32ca73deb87fa11d02831de04ca5aee4f7f339edb1c2bdb42782a97529ef8b41eb8990761e932b13deec886c78029e34d83239f96abaf850bbb5381db17666f5a156a&orgExt",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=3528691")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 원소 전문가 업적 정리",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbAxIO4%2FbtqPql9guHY%2Fk24uKF85IjFtWoWDmjPAD1%2Fimg.png",
//"https://like-tea.tistory.com/232")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 삐카? 왜 또 삐카야? 업적 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F9NIaG%2FbtrnQkpF2ZX%2FGS6tgbSOtdAZoSvhyV9Bw1%2Fimg.png",
//"https://gamecat.tistory.com/80")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 이나즈마 히든 업적 음식 문제 - 미식 문답 일일 퀘스트",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbiM0D0%2FbtrdqL4PmMA%2FHPbm7BTQFdH9oKegKW2PwK%2Fimg.png",
//"https://iteastory.com/210")
//)
//myRef.push().setValue(
//ContentModel(
//"무사의 숙명, 인간으로서의 조건(이나즈마 히든 업적)",
//"https://static.inven.co.kr/column/2021/07/28/news/i14599989220.png",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=259928")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 비글호 표류기",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fk1JKH%2Fbtrd0ziek4D%2FWq5c7vcOU5Qes7BQKNNacK%2Fimg.png",
//"https://flatsun.tistory.com/2535")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 업적 특별할 거 없어, 단지 익숙할 뿐 간단하게 깨는 법",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FsI1pW%2FbtqKRrSxhCg%2FSjdDhZFc6gHqGwyaKQNJYk%2Fimg.jpg",
//"https://dakkong.tistory.com/57#gsc.tab=0")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 히든업적 이건 비상식량이 아니야 일일 퀘스트 음식 주문서 공략",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F4Ozkg%2Fbtq26nUlmzC%2FP53vAqNoKP5PLqCKm5XrhK%2Fimg.png",
//"https://like-tea.tistory.com/334")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 더이상 늙지 않아 업적(혈곡 무덤 위치) [ 이즈나마 업적 퀘스트 ]",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbnnz4e%2FbtraBcXq3PE%2F1bzP0HU3Kj4NjbSFsD9Ikk%2Fimg.png",
//"https://worth.tistory.com/120")
//)
//myRef.push().setValue(
//ContentModel(
//"성실한 키다리 실버 (히든 업적)",
//"https://static.inven.co.kr/column/2021/09/08/news/i16411584112.png",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=262103")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 숨겨진 히든 업적 파울 하는 방법 공략",
//"https://postfiles.pstatic.net/MjAyMTAyMDlfODUg/MDAxNjEyODM3MTQxOTUw.T1PqJhh1vWOHvR_aEZoWACUTr7_B88kTpH98lZzc4wcg.YZMrMewQHQSrfG7V6MLK2Ov-LorAplsJydqeKepVAD4g.JPEG.leejt6614/SE-4f562a7f-d035-4de2-90cc-be588d1bb06f.jpg?type=w966",
//"https://blog.naver.com/leejt6614/222237645612")
//)
//myRef.push().setValue(
//ContentModel(
//"버섯을 끼쳐서 죄송! (이나즈마 판매원 연계)",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdiCS4z%2FbtreLK4i7aj%2FrwtRKST3dWaIue69zjjCj0%2Fimg.jpg",
//"https://iteastory.com/214")
//)
//tip
//myRef.push().setValue(
//ContentModel(
//"한줄 팁 모음",
//"https://static.inven.co.kr/column/2020/10/04/news/i16287366404.jpg",
//"https://genshin.inven.co.kr/dataninfo/guide/?bidx=245261")
//)
//myRef.push().setValue(
//ContentModel(
//"[원신] 원신 플레이 팁 정리",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbqPBTG%2Fbtq09zib8AD%2Fqq0p30E7DCkWXLuCHu9PJ1%2Fimg.jpg",
//"https://ghostweb.tistory.com/1155")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 - 기행 초보자 팁",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbO3ScZ%2FbtqQPzKIRom%2FIRYoSWhK4dHLgU68wFMLI1%2Fimg.jpg",
//"https://identity-five.tistory.com/15")
//)
//myRef.push().setValue(
//ContentModel("원신 - 무과금으로 즐기는 팁",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FCXLem%2FbtroT6hsVdI%2FBOKMR5Myw8dA14FiB7jSzk%2Fimg.png",
//"https://asecurity.dev/entry/%EC%9B%90%EC%8B%A0-%EB%AC%B4%EA%B3%BC%EA%B8%88%EC%9C%BC%EB%A1%9C-%EC%A6%90%EA%B8%B0%EB%8A%94-%ED%8C%81")
//)
//myRef.push().setValue(
//ContentModel(
//"과금 효율 계산",
//"https://ac2-p2.namu.la/20210424/03e780864f021ed2b46918ad1a74fa4182bcea298cdbbb995682bb28810bfdb6.png",
//"https://arca.live/b/genshin/25061914")
//)
//myRef.push().setValue(
//ContentModel(
//"속세의 주전자 시스템 Tip - 신뢰 등급편",
//"https://ac2.namu.la/20210429/1091e0714a1bd7b4a7d455c3297ac65152cd652e78e31440576fad5290192e90.png",
//"https://arca.live/b/genshin/25328480?p=1")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 공략 가이드 팁 낚시터 위치",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHOMew%2FbtrlDcUCUn8%2Fycn50roywNvAEc4ZUMdKSK%2Fimg.jpg",
//"https://psg4.tistory.com/1149")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 상자 쉽게 찾기 팁 공유",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbzcPK4%2FbtqLU9LLYnM%2F8vGOSnkhjkjfSHIrVtuaIk%2Fimg.png",
//"https://djhand.tistory.com/589")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 캐릭터/무기 레벨업 재화 필요개수 정리",
//"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FG4UrM%2FbtqTKMnbEWP%2FTseUG1owC5LZIhzIeZin6K%2Fimg.png",
//"https://pangya.tistory.com/90")
//)
//기타
//myRef.push().setValue(
//ContentModel(
//"원신의 언어를 분석 해봤다",
//"https://ac-p2.namu.la/0f/0f5bd155f3af6c80167f4e01b2e16783cbd7a2d83c5e33175dfacca0565cc3a4.png",
//"https://arca.live/b/genshin/8428607")
//)
//myRef.push().setValue(
//ContentModel(
//"츄츄족의 언어를 분석 해보았다 1편",
//"https://ac2.namu.la/a9/a93e05e6e85f7cd6438bbd319d3ab32059a5a46c6e7c4913abcba1c1f0816b18.png",
//"https://arca.live/b/genshin/8823371")
//)
//myRef.push().setValue(
//ContentModel(
//"(재업)몬드지역 전망 포인트 위치(지리지 도감)",
//"https://dcimg1.dcinside.com/viewimage.php?id=22b3c32eecdc28b461b5d3b602&no=24b0d769e1d32ca73dec84fa11d0283195504478ca9b7677dc322d30c8349b5a787c0ee24c8a034fb7a4823065c90c8e52c360f5ffedf7f299d3745e43074441e5d6400a7ac738b2&orgExt",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=573851")
//)
//myRef.push().setValue(
//ContentModel(
//"(스압) 리월지역 전망 포인트 위치 (지리지 도감)",
//"https://dcimg8.dcinside.co.kr/viewimage.php?id=22b3c32eecdc28b461b5d3b602&no=24b0d769e1d32ca73dec84fa11d0283195504478ca9b7677dc322d30c8379b5ae2809836024f8773000a206e99e3b5ff5fc8336d8c0124fc901a6554dc22a02e5afe1c7e86a0a4ba",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=589931")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 환경 디테일.gif",
//"https://dcimg1.dcinside.com/viewimage.php?no=24b0d769e1d32ca73dec84fa11d0283195504478ca9b7677dc322d30c83c9b5adb27f388f8e31c8a9016af285475261199d4da112dbcb38dc5e64e1b2b1df4fd4a62cfdca6e2ca97eee968967effb5002509a1c95f9be232966c9825ee2629172835cee21914c1b9",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=660723")
//)
//myRef.push().setValue(
//ContentModel(
//"원신 캐릭들의 평타모션 (데이터 주의 120mb 꽊 채움)",
//"https://dcimg1.dcinside.com/viewimage.php?id=22b3c32eecdc28b461b5d3b602&no=24b0d769e1d32ca73dec84fa11d0283195504478ca9b7677dc322d30cb379b5a0396a0be4575bcaa00a976f5fcd09eea0a8f435f4f8db2764c6578faaf327f00568e4ab3d4c3bd6ae0&orgExt",
//"https://gall.dcinside.com/mgallery/board/view/?id=onshinproject&no=687074")
//)