# FavoriteTrackList

MVVM 패턴과 Repository 패턴을 기반으로 구현하였습니다.

**사용 기술**

`Room` - 편한 로컬 데이터 저장을 위해 사용하였습니다.

`Retrofit2` - 편한 API 통신을 위해 사용하였습니다.

`Coroutine` - 비동기 처리를 위해 사용하였습니다.

`Koin` - 의존성 주입을 위해 사용하였습니다.

`Glide` - 편한 이미지 로딩을 위해 사용하였습니다.



보완 해야할 점

1. 전체적인 코드 네이밍

2. ReplaceFragment 시 객체를 만들어 넘기는 것이 아닌 
객체를 만드는 함수를 넘기는 식으로 변경(불필요한 인스턴스 줄이기)

3. Flow 활용해보기

4. 공통된 로직의 분리

5. 불필요한 Scope Function 줄이기

6. 좋아요 리스트를 LiveData 로 가져와서 사용하기

7. ConfigurationChange 상황 대처 하기

8. ViewModel을 나누거나 불필요한 데이터를 알 필요 없게 하기