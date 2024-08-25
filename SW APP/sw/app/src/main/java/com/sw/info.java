package com.sw;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class info extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View self;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public info() {
        // Required empty public constructor
    }

    private List<List<Main>> mainStack;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static info newInstance(String param1, String param2) {
        info fragment = new info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private MainAdapter adapter;
    private LinearLayoutManager manager;

    private RecyclerView rcvMain;

    //Mains를 Stack형식으로 쌓는다.
    private void changeMainSet(List<Main> lMains) {
        if (mainStack == null)
            mainStack = new ArrayList<>();

        mainStack.add(lMains);

        if (mainStack.size() > 0)
            adapter.setMains(mainStack.get(mainStack.size() - 1));

        adapter.notifyDataSetChanged();
    }

    //뒤로가기 눌렀을때의 행동
    public boolean back() {
        if (mainStack == null || mainStack.size() <= 1) {
            return false;
        }
        mainStack.remove(mainStack.size() - 1);
        adapter.setMains(mainStack.get(mainStack.size() - 1));
        adapter.notifyDataSetChanged();

        return true;
    }

    private void initRecyclerView() {

        if (adapter == null) {
            adapter = new MainAdapter();
        }

        if (manager == null) {
            manager = new LinearLayoutManager(getContext());
        }

        if (rcvMain == null) {
            rcvMain = self.findViewById(R.id.rcv_main);
        }
        rcvMain.setAdapter(adapter);
        rcvMain.setLayoutManager(manager);
    }

    private List<Main> createInitBtnArray() {
        List<Main> mains = new ArrayList<>();

        mains.add(new Main(
                "프로그래밍",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet());
                    }
                }
        ));
        mains.add(new Main(
                "빅데이터",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet1());
                    }
                }
        ));
        mains.add(new Main(
                "네트워크 & 보안",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet2());
                    }
                }
        ));
        mains.add(new Main(
                "AI",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet3());
                    }
                }
        ));

        return mains;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        self = view;

        initRecyclerView();
        changeMainSet(createInitBtnArray());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d("MainFragment", "Start!");

        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    private List<Main> changeMainSet() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("프로그래밍 소개", null);
        mMain.setBtnDescription("[프로그래밍]\n" +
                "컴퓨터 프로그래밍(영어: computer programming) 또는 간단히 프로그래밍(programming, 문화어: 프로그램 작성) 혹은 코딩(coding)은 하나 이상의 관련된 추상 알고리즘을 특정한 프로그래밍 언어를 이용해 구체적인 컴퓨터 프로그램으로 구현하는 기술을 말한다. 프로그래밍은 기법, 과학, 수학, 공학, 심리학적 속성들을 가지고 있다.\n" +
                "\n" +
                "한편 코딩은 '작업의 흐름에 따라 프로그램 언어의 명령문을 써서 프로그램을 작성하는 일' 또는 '프로그램의 코드를 작성하는 일'로 크게 나누어 언급되고 있는데 이는 알고리즘과의 상관관계를 잘 언급하고 있다.\n" +
                "\n" +
                "[프로그래밍과 코딩]\n" +
                "프로그래밍이란 '컴퓨터 프로그램을 작성하는 일'을 뜻하며 코딩이란 프로그래밍에 수반되는 '코드 작성 작업'을 의미한다. 프로그래밍은 '일반적으로 프로그램 작성 방법의 결정, 코딩(coding), 에러 수정 따위의 작업을 이르지만, 특수하게 코딩만을 이를 때도 있다.\n" +
                "\n" +
                "[프로그래밍 언어]\n" +
                "특정한 프로그래밍 언어로 쓰인 프로그램은 기계어로 번역되어 컴퓨터에 의해 실행되며, 어떤 프로그래밍 언어도 기계어로 번역이 가능하다. 어떤 언어에서는 기계어 대신 p-부호로 불리는 바리를 생성하기도 한다. 프로그래머가 기계 부호로 직접 작성하는 것도 가능하지만, 이는 굉장히 어려운 작업이다. 때문에 낮은 수준에서의 컴퓨터 제어가 필요한 경우 프로그래머들은 기계어 명령어에 대한 일대일 연상 기호 대응인 어셈블리어를 사용한다.\n" +
                "\n" +
                "서로 다른 프로그래밍 언어는 다른 프로그래밍 유형을 지원하기 때문에, 분야에 따라 적합한 언어가 존재한다. 또한 언어마다 프로그래머가 알고리즘을 구현할 때 그 구체적인 방법과 수준의 차이가 있기 때문에, 사용의 편의성과 성능 사이에서 적절한 타협이 이루어진다. 또한 프로그래밍의 언어 중 하나이며 컴퓨터에게 명령을 전달할 수 있다.\n\n\n출처\n" +
                "네이버 위키백과 - 컴퓨터 프로그래밍");
        mains.add(mMain);

        mains.add(new Main(
                "취업 정보",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet4());
                    }
                }


        ));
        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet1() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("빅데이터 소개", null);
        mMain.setBtnDescription("[빅데이터]\n" +
                "디지털 경제의 확산으로 우리 주변에는 규모를 가늠할 수 없을 정도로 많은 정보와 데이터가 생산되는 '빅데이터(Big Data)' 환경이 도래하고 있다. 빅데이터란 과거 아날로그 환경에서 생성되던 데이터에 비하면 그 규모가 방대하고, 생성 주기도 짧고, 형태도 수치 데이터뿐 아니라 문자와 영상 데이터를 포함하는 대규모 데이터를 말한다.\n" +
                "\n" +
                "[빅데이터의 특징]\n" +
                "빅데이터의 특징으로는 크기(Volume), 속도(Velocity), 다양성(Variety)을 들 수 있다. 크기는 일반적으로 수십 테라 바이트 혹은 수십 페타바이트 이상 규모의 데이터 속성을 의미한다.\n" +
                "\n" +
                "속도는 대용량의 데이터를 빠르게 처리하고 분석할 수 있는 속성이다. 융복합 환경에서 디지털 데이터는 매우 빠른 속도로 생산되므로 이를 실시간으로 저장, 유통, 수집, 분석처리가 가능한 성능을 의미한다. 다양성(Variety)은 다양한 종류의 데이터를 의미하며 정형화의 종류에 따라 정형, 반정형, 비정형 데이터로 분류할 수 있다.\n" +
                "\n" +
                "[빅데이터의 플랫폼]\n" +
                "빅데이터 플랫폼은 빅데이터 기술의 집합체이자 기술을 잘 사용할 수 있도록 준비된 환경이다. 기업들은 빅데이터 플랫폼을 사용하여 빅데이터를 수집, 저장, 처리 및 관리 할 수 있다. 빅데이터 플랫폼은 빅데이터를 분석하거나 활용하는 데 필요한 필수 인프라(Infrastructure)인 셈이다. 빅데이터 플랫폼은 빅데이터라는 원석을 발굴하고, 보관, 가공하는 일련의 과정을 이음새 없이(Seamless) 통합적으로 제공해야 한다.\n" +
                "\n" +
                "\n" +
                "출처\n" +
                "[네이버 지식백과] 빅데이터 정의 (빅데이터, 2013. 2. 25., 정용찬)\n" +
                "[네이버 지식백과] 빅데이터란? (국립중앙과학관 - 빅데이터)");
        mains.add(mMain);

        mains.add(new Main(
                "취업 정보",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet5());
                    }
                }

        ));


        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet2() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("네트워크 & 보안 소개", null);
        mMain.setBtnDescription("[네트워크]\n" +
                "랜(LAN)이나 모뎀 따위의 통신 설비를 갖춘 컴퓨터를 이용하여 서로 연결시켜 주는 조직이나 체계.\n" +
                "\n" +
                "[네트워크의 응용과 전망]\n" +
                "최근 컴퓨터 및 통신분야에서 기술혁신이 추진되어 전기통신 수단에 의한 복수의 컴퓨터 결합이 제창되고 있다. 이 경우 컴퓨터와 단말을 연결하는 네트워크 어떻게 구성할 것인가가 효율적인 컴퓨터 이용을 실현하는데 있어서 중요한 의미를 갖는다. 그러한 네트워크 구축기술로서 네트워크 아키텍처의 개발이 최근 활발하게 추진되고 있다.\n" +
                "\n" +
                "\n" +
                "[보안]\n" +
                "데이터베이스에 저장되어 있는 데이터는 조직을 운영하는 데 꼭 필요한 것으로, 조직 내 사용자들이 공유하여 사용한다. 만약 사용이 허락되지 않은 외부인이 데이터베이스에 침입하여 데이터를 유출하거나 손상한다면 조직에 치명적 손실이 발생할 것이다. 그러므로 조직에서 허가한 사용자만 데이터베이스에 접근할 수 있도록 통제하여 보안을 유지하는 일이 무척 중요하다.\n" +
                "\n" +
                "[보안을 유지하여 데이터를 보호하는 방법]\n" +
                "① 물리적 환경에 대한 보안\n" +
                "자연 재해처럼 데이터베이스에 물리적으로 손실을 발생시키는 위험으로부터 데이터베이스를 보호해야 한다.\n" +
                "\n" +
                "② 권한 관리를 통한 보안\n" +
                "접근이 허락된 사용자만 부여된 권한 내에서 데이터베이스를 사용할 수 있도록 한다. 그러려면 계정이 발급된 사용자만 데이터베이스에 접근할 수 있도록 통제하고, 각 사용자별로 데이터베이스의 사용 범위와 수행 가능한 작업 내용을 제한할 수 있어야 한다.\n" +
                "\n" +
                "③ 운영 관리를 통한 보안\n" +
                "접근이 허락된 사용자가 부여된 권한 내에서 데이터베이스를 사용하더라도 실수 등의 이유로 데이터 무결성을 위반하는 행동을 할 수 있다. 그러므로 데이터 무결성을 유지하기 위한 올바른 제약조건을 정의하고, 사용자들이 정의된 제약조건을 위반하지 않도록 통제해야 한다.\n" +
                "\n" +
                "출처\n" +
                "[네이버 지식백과] 네트워크 [network] (첨단산업기술사전, 1992. 5. 1., 일본통상성)\n" +
                "[네이버 지식백과] 보안 (데이터베이스 개론, 2013. 6. 30., 김연희)\n");
        mains.add(mMain);

        mains.add(new Main(
                "취업 정보",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet6());
                    }
                }

        ));



        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet3() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("AI 소개", null);
        mMain.setBtnDescription("[인공지능]\n" +
                "컴퓨터가 인간의 지능 활동을 모방할 수 있도록 하는 것을 인공지능이라 한다. 즉 인간의 지능이 할 수 있는 사고·학습·모방·자기 계발 등을 컴퓨터가 할 수 있도록 연구하는 컴퓨터공학 및 정보기술 분야를 말한다\n" +
                "\n" +
                "[인공지능 활용]\n" +
                "① 자연언어처리(natural language processing) 분야에서는 이미 자동번역과 같은 시스템을 실용화하며, 특히 연구가 더 진행되면 사람이 컴퓨터와 대화하며 정보를 교환할 수 있게 되므로 컴퓨터 사용에 혁신적인 변화가 오게 될 것이다.\n" +
                "\n" +
                "② 전문가시스템(expert system) 분야에서는 컴퓨터가 현재 인간이 하고 있는 여러 가지 전문적인 작업들(의사의 진단, 광물의 매장량 평가, 화합물의 구조 추정, 손해 배상 보험료의 판정 등)을 대신할 수 있도록 하는 것이다. 여러 분야 가운데서도 가장 일찍 발전하였다.\n" +
                "\n" +
                "③ 컴퓨터가 TV 카메라를 통해 잡은 영상을 분석하여 그것이 무엇인지를 알아내거나, 사람의 목소리를 듣고 그것을 문장으로 변환하는 것 등의 일은 매우 복잡하며, 인공지능적인 이론의 도입 없이는 불가능하다. 이러한 영상 및 음성 인식은 문자 인식, 로봇 공학 등에 핵심적인 기술이다.\n" +
                "\n" +
                "④ 이론증명(theorem proving)은 수학적인 정리를 이미 알려진 사실로부터 논리적으로 추론하여 증명하는 과정으로서 인공지능의 여러 분야에서 사용되는 필수적인 기술이며, 그 자체로도 많은 가치를 지니고 있다.\n" +
                "\n" +
                "⑤ 신경망(neural net)은 비교적 근래에 등장한 것으로서 수학적 논리학이 아닌, 인간의 두뇌를 모방하여 수많은 간단한 처리기들의 네트워크로 구성된 신경망 구조를 상정하는 것이다.\n" +
                "\n" +
                "\n" +
                "출처\n" +
                "[네이버 지식백과] 인공지능 [Artificial Intelligence] (손에 잡히는 IT 시사용어, 2008.02.01)\n" +
                "[네이버 지식백과] 인공지능 [artificial intelligence, 人工知能] (두산백과)");
        mains.add(mMain);

        mains.add(new Main(
                "취업 정보",
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeMainSet(changeMainSet7());
                    }
                }

        ));


        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }


    private List<Main> changeMainSet4() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("게임 프로그래머", null);
        mMain.setBtnDescription("• 하는 일\n" +
                "게임프로그램의 구조를 설계하고 게임프로그래밍을 수행한다.\n" +"\n"+
                "• 업무수행능력\n" +
                "전산, 범주화, 문제 해결, 고장의 발견 및 수리, 기술 분석\n\n출처\n워크넷");
        mains.add(mMain);

        Main mMain2 = new Main("웹프로그래머", null);
        mMain2.setBtnDescription("• 하는 일\n" +
                "웹상에서 각종 자료들을 보여줄 수 있도록 웹 프로그래밍 언어를 이용하여 프로그램을 설계하고 작성한다.\n" +
                "\n"+"• 자격증\n" +
                "OCP(외국), SCJP(외국), 정보처리기능사, 산업기사, 기사(국가기술)\n" +
                "\n"+"• 업무수행능력\n" +
                "전산, 기술 설계, 설치, 기술 분석, 창의력\n\n출처\n워크넷");
        mains.add(mMain2);

        Main mMain3 = new Main("응용소프트웨어 개발자", null);
        mMain3.setBtnDescription("• 하는 일\n" +
                "응용소프트웨어개발자는 개인이나 기업체에서 필요로 하는 응용소프트웨어를 개발하기 위해 기존에 출시된 응용소프트웨어에 대한 시장조사, 소프트웨어의 용도 파악, 고객의 요구 수렴 등을 거쳐 전체적인 개발계획을 세운다. 이후 응용소프트웨어 개발을 위한 설계 작업을 수행한다.\n\n출처\n워크넷");
        mains.add(mMain3);

        Main mMain4 = new Main("컴퓨터 프로그래머", null);
        mMain4.setBtnDescription("• 하는 일\n" +
                "개발 의도에 적합한 컴퓨터 언어를 사용하여 프로그램 구조를 설계하고 프로그래밍한다.\n" +
                "\n" +
                "• 자격증\n" +
                "전자계산기기능사, 전자계산조직응용기사, 기술사(국가기술), 정보관리기술사(국가기술)\n" +
                "\n" +
                "• 업무수행능력\n" +
                "전산, 창의력, 기술 설계, 조작 및 통제, 장비의 유지\n\n출처\n워크넷");
        mains.add(mMain4);

        Main mMain5 = new Main("RFID시스템 개발자", null);
        mMain5.setBtnDescription("• 하는 일\n" +
                "RFID 기술을 응용하여 새로운 제품을 설계하거나, 다양한 정보를 신속하게 수집할 수 있도록 정보서비스를 개발하고 설계한다.\n" +
                "\n" +
                "• 업무수행능력\n" +
                "전산, 기술 설계, 설치, 작동 점검, 기술 분석\n\n출처\n워크넷");
        mains.add(mMain5);

        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet5() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("데이터베이스 개발자", null);
        mMain.setBtnDescription("• 하는 일\n" +
                "데이터베이스개발자는 방대한 데이터들을 효율적으로 처리, 관리할 수 있는 오라클, MSSQL, My SQL, 인포믹스, 사이베이스, DB2 등의 툴을 이용해서 정형화된 데이터를 구축한 데이터베이스 관리시스템(DBMS：DataBase Management System)을 설계·개발하고 관리한다. 또한, 고객의 금융 정보와 같은 자료를 저장하고 조직화하는 특화된 소프트웨어를 사용하여 해당 자료를 고객이 원할 때 활용할 수 있도록 하고 허가되지 않은 접근으로부터 보호한다.\n\n출처\n워크넷");
        mains.add(mMain);

        Main mMain2 = new Main("빅데이터 분석가", null);
        mMain2.setBtnDescription("• 하는 일\n" +
                "사람들의 행동 패턴 또는 시장의 경제상황 등을 예측하며 데이터 속에 함축된 트렌드나 인사이트를 도출하고 이로부터 새로운 부가가치를 창출하기 위해 대량의 빅데이터를 관리하고 분석한다.\n" +
                "\n" +
                "• 업무수행능력\n" +
                "범주화, 글쓰기, 전산, 시간 관리, 논리적 분석\n\n출처\n워크넷");
        mains.add(mMain2);

        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet6() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("네트워크 관리자", null);
        mMain.setBtnDescription("• 하는 일\n" +
                "클라이언트, 서버, 인터넷 및 인트라넷 형태의 전산망 관련 하드웨어 및 소프트웨어\n" +
                "\n" +
                "• 자격증\n" +
                "전자계산기조직응용기사, 기술사(국가기술), 정보관리기술사(국가기술), 정보처리기능사, 산업기사, 기사(국가기술)\n" +
                "\n" +
                "• 업무수행능력\n" +
                "고장의 발견 및 수리, 조작 및 통제, 장비의 유지, 설치, 작동 점검\n\n출처\n워크넷");
        mains.add(mMain);

        Main mMain2 = new Main("네트워크 엔지니어", null);
        mMain2.setBtnDescription("• 하는 일\n" +
                "사용자의 요구사항에 부합하는 네트워크시스템을 분석·설계·구축한다.\n" +
                "\n" +
                "• 자격증\n" +
                "전자계산기조직응용기사, 기술사(국가기술), 정보관리기술사(국가기술), 정보처리기능사, 산업기사, 기사(국가기술)\n" +
                "\n" +
                "• 업무수행능력\n" +
                "장비의 유지, 작동 점검, 설치, 조작 및 통제, 고장의 발견 및 수리\n\n출처\n워크넷");
        mains.add(mMain2);

        Main mMain3 = new Main("컴퓨터보안전문가", null);
        mMain3.setBtnDescription("• 하는 일\n" +
                "지식정보화사회의 중요한 정보시스템과 정보자산을 보호하기 위해 보안정책을 수립하고, 시스템에 대한 접근 및 운영을 통제하며, 침입 발생시 신속히 탐지하여 즉각적으로 대응·복구한다.\n" +
                "\n" +
                "• 자격증\n" +
                "CISA[국제공인정보시스템 감사사](외국), CISSP[국제공인정보시스템 보안전문가](외국), 정보보안기사∙산업기사(국가기술)\n" +
                "\n" +
                "• 업무수행능력\n\n출처\n워크넷");
        mains.add(mMain3);

        Main mMain4 = new Main("컴퓨터시스템 설계 및 분석가", null);
        mMain4.setBtnDescription("• 하는 일\n" +
                "조직 운영에 필요한 효율적인 정보시스템을 구축하기 위해 이용자의 요구사항과 사용 환경, 기술특성 등을 분석하고, 최신 정보통신기술을 이용하여 시스템을 설계하며, 시스템 구축 완료 후 사용되기까지의 모든 업무를 총괄한다. \n" +
                "\n" +
                "• 자격증\n" +
                "DB2(외국), 전자계산기기능사, 기사, 기술사(국가기술), 전자계산조직응용기사, 기술사(국가기술), 정보관리기술사(국가기술)\n\n출처\n워크넷");
        mains.add(mMain4);

        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

    private List<Main> changeMainSet7() {
        List<Main> mains = new ArrayList<>();

        Main mMain = new Main("인공지능 전문가", null);
        mMain.setBtnDescription("• 하는 일\n" +
                "인간에 대한 이해를 바탕으로 컴퓨터와 로봇 등이 인간과 같이 사고하고의사결정할 수 있도록 인공지능 알고리즘 또는 프로그램을 구현하는 기술을 개발한다. 연구자들은 인공지능을 개발하기 위하여 실제 다양한 분야의 소프트웨어를 개발한다. \n" +
                "\n" +
                "• 자격증\n" +
                "데이터분석전문가, 정보처리기사\n\n출처\n워크넷");
        mains.add(mMain);

        adapter.notifyDataSetChanged();
        return mains;
        //  adapter.notifyItemRangeChanged(0,mains.size());
    }

}