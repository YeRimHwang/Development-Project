using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StartMusic : MonoBehaviour
{
    static public StartMusic instance;
    // Start is called before the first frame update
    void Start()
    {
        if( instance == null)
        {
            DontDestroyOnLoad(this.gameObject);
            instance = this;
        }
        else // (instance != null)
        {
            Destroy(this.gameObject);
        }
    }
}
