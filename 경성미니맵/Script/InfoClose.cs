using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class InfoClose : MonoBehaviour
{
    private GameObject target;
    public GameObject Info;
    bool isClick = false;
    AudioSource audioSrc;
    // Start is called before the first frame update
    void Start()
    {
        audioSrc = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetMouseButtonDown(0))
        {
           CastRay();
           if(target==this.gameObject)
           {
               isClick = true;
               if(isClick)
                {
                    audioSrc.Play();
                }
                Invoke("InfoCall",0.2f);
           }
        }
    }

    void CastRay()
    {
        target=null;
        Vector2 pos = Camera.main.ScreenToWorldPoint(Input.mousePosition);
        RaycastHit2D hit = Physics2D.Raycast(pos,Vector2.zero,0f);

        if(hit.collider !=null)
        {
            target=hit.collider.gameObject;
        }
    }

    void InfoCall()
    {
        Info.SetActive(false);
    }
}
