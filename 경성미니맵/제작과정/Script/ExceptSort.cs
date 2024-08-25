using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ExceptSort : MonoBehaviour
{
    SpriteRenderer sr;
    private Move Player;
    // Start is called before the first frame update
    void Start()
    {
        sr = GetComponent<SpriteRenderer>();
        Player = FindObjectOfType<Move>();
    }

    // Update is called once per frame
    void Update()
    {
        if(Player.transform.position.y<transform.position.y)
        {
            sr.sortingOrder = 3;
        }
        else
        {
            sr.sortingOrder = 6;
        }
    }
}
