using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Gbutton : MonoBehaviour
{
    private GameObject target;
    private Move Player;
    bool isClick = false;
    AudioSource audioSrc;
    // Start is called before the first frame update
    void Start()
    {
        Player = FindObjectOfType<Move>();
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
                Invoke("SceneCall",0.2f);
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

    void SceneCall()
    {
        SceneManager.LoadScene("Scene15");
         Player.transform.position = new Vector2(4,-4);
    }
}
