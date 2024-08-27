using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Move : MonoBehaviour
{   
    static public Move instance;
    private BoxCollider2D BoxCollider;
    private Rigidbody2D rigid2D;
    public float speed;
    private Vector3 vector;
    public float runspeed;
    private float applyRunSpeed;
    private bool applyRungFlag = false;
    public int walkCount;
    private int currentWalkCount;
    private bool canMove = true;
    bool isMoving = false;
    AudioSource audioSrc;

    private Animator animator;

    // Start is called before the first frame update
    void Start()
    {
        if(instance == null)
        {
            DontDestroyOnLoad(this.gameObject);
            BoxCollider = GetComponent<BoxCollider2D>();
            rigid2D = GetComponent<Rigidbody2D>();
            animator = GetComponent<Animator>();
            audioSrc = GetComponent<AudioSource>();
            instance = this;
        }
        else // (instance != null)
        {
            Destroy(this.gameObject);
        }
        
    }

    IEnumerator MoveCoroutinue()
    {
        Vector3 viewPos = Camera.main.WorldToViewportPoint(transform.position); //캐릭터의 월드 좌표를 뷰포트 좌표계로 변환해준다.
        viewPos.x = Mathf.Clamp01(viewPos.x); //x값을 0이상, 1이하로 제한한다.
        viewPos.y = Mathf.Clamp01(viewPos.y); //y값을 0이상, 1이하로 제한한다.
        Vector3 worldPos = Camera.main.ViewportToWorldPoint(viewPos); //다시 월드 좌표로 변환한다.
        transform.position = worldPos; //좌표를 적용한다.

        while(Input.GetAxisRaw("Vertical") != 0 || Input.GetAxisRaw("Horizontal") != 0)
        {
        if(Input.GetKey(KeyCode.LeftShift))
                {
                    applyRunSpeed = runspeed;
                    applyRungFlag = true;
                }
                else{
                    applyRunSpeed = 0;
                    applyRungFlag = false;
                }

                vector.Set(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical"),transform.position.z);
                
                if(vector.x != 0 || vector.y != 0)
                {
                     isMoving = true;
                }
                else
                {
                    isMoving = false;
                }
                if(isMoving)
                {
                    if(!audioSrc.isPlaying)
                    {
                        audioSrc.Play();
                    }
                }
                else{
                    audioSrc.Stop();
                }
                
                if(vector.x != 0)
                {
                    vector.y = 0;
                }

                animator.SetFloat("DirX",vector.x);
                animator.SetFloat("DirY",vector.y);

                animator.SetBool("Walking",true);
                
                while(currentWalkCount < walkCount)
                {    
                    if(vector.x != 0)
                    {
                        transform.Translate(vector.x * (speed + applyRunSpeed), 0, 0);
                        rigid2D.velocity = Vector3.zero;
                    }
                    else if(vector.y != 0)
                    {
                        transform.Translate(0,vector.y * (speed + applyRunSpeed), 0);
                        rigid2D.velocity = Vector3.zero;
                    }
                    if(applyRungFlag)
                    {
                        currentWalkCount++;
                    }
                    currentWalkCount++;
                    yield return new WaitForSeconds(0.01f);
                }
                currentWalkCount = 0;
        }
        animator.SetBool("Walking",false);
        canMove = true;
    }


    // Update is called once per frame
    void Update()
    {
        if(Input.GetAxisRaw("Horizontal") != 0 || Input.GetAxisRaw("Vertical") !=0)
        {
            canMove = false;
                StartCoroutine(MoveCoroutinue());
        }
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Application.Quit();
        }

    }
}