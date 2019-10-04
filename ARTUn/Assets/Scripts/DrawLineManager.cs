using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DrawLineManager : MonoBehaviour
{


    public GameObject gameObject;
    public GameObject camera;

    //	private GraphicsLineRenderer currLine;
    //private LineRenderer currLine;
    private int numClicks = 0;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButton(0) || Input.touchCount > 0)
        {
            Debug.Log("Touched");


            //GameObject go = new GameObject();

            //go.AddComponent<MeshFilter>();
            //	go.AddComponent<MeshRenderer>();

            //currLine = go.AddComponent<GraphicsLineRenderer>();
            //currLine = go.AddComponent<LineRenderer>();
            //currLine.SetWidth(1.0f, 1.0f);
            //currLine.SetColors(Color.black, Color.black);

            numClicks = 0;
            Vector3 camPos = camera.transform.position;
            Vector3 camDirection = camera.transform.forward;
            Quaternion camRotation = camera.transform.rotation;
            float spawnDistance = 2;
            Debug.Log("Touched" + camPos.x + " " + camPos.y + " " + camPos.z);
            Vector3 spawnPos = camPos + (camDirection * spawnDistance);
            Debug.Log(spawnPos);
            //currLine.AddPoint(spawnPos);
           // currLine.SetVertexCount(numClicks + 1);
            //currLine.SetPosition(numClicks, spawnPos);
            numClicks++;




            GameObject cur = Instantiate(gameObject, spawnPos,  camRotation);
            cur.transform.SetParent(this.transform);
        }





    }
}
