using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{
    public GameObject map;
    public bool state;
    AudioSource audioSrc;
    // Start is called before the first frame update
    void Start()
    {
        DontDestroyOnLoad(this.gameObject);
        audioSrc = GetComponent<AudioSource>();
        state=false;
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Application.Quit();
        }
        if (Input.GetKeyDown(KeyCode.M))
        {
            audioSrc.Play();
            Invoke("MapCall",0.2f);
        }
   
    }
     void MapCall()
    {
        if(state==false)
        {
            map.SetActive(true);
            state=true;
        }
        else{
            map.SetActive(false);
            state=false;
        }
    }
}
