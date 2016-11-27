/*========================
 * name: snake
 *coder:bearer
 * version: v1.00
 *========================*/
 
#include "StdAfx.h"
#include "Game.h"
#include<iostream>
#include<string>
using namespace std;

int main(int argc,char *argv[])
{
	int speed;
	int s = 0;
	do{
	cout<<"**********************************贪吃蛇************************************"<<endl;
	cout<<"游戏规则：方向上下左右控制蛇的移动，P键暂停，Q键离开！"<<endl;
	cout<<"请选择关卡，1-10关"<<endl;
	cin>>speed;

	if(speed<1||speed>10)
		cout<<"一定要1-10啊"<<endl;
	}while ((speed<1)||(speed>10));

    Game::INTERVAL=1000/speed;
	Game game(480,320,16,24,&s);
	cout<<"请输入验证码："<<endl;
	int answer;
	while(game.verifycode(answer)!=1)
	{
		cout<<"请输入验证码："<<endl;
	}
	if(!game.init()) return 1;
	game.start();

	string sname;
	cout<<"高人，请留下姓名！"<<endl;
	cin>>sname;
	FILE *f = fopen("score.txt","a+");
	if(f != NULL)
	{
		fprintf(f,"%s-------%20d\n",sname.c_str(),s);	
	}
	cout<<"*****************************************************************************"<<endl;
	system("pause");
    return 0;
}