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
	cout<<"**********************************̰����************************************"<<endl;
	cout<<"��Ϸ���򣺷����������ҿ����ߵ��ƶ���P����ͣ��Q���뿪��"<<endl;
	cout<<"��ѡ��ؿ���1-10��"<<endl;
	cin>>speed;

	if(speed<1||speed>10)
		cout<<"һ��Ҫ1-10��"<<endl;
	}while ((speed<1)||(speed>10));

    Game::INTERVAL=1000/speed;
	Game game(480,320,16,24,&s);
	cout<<"��������֤�룺"<<endl;
	int answer;
	while(game.verifycode(answer)!=1)
	{
		cout<<"��������֤�룺"<<endl;
	}
	if(!game.init()) return 1;
	game.start();

	string sname;
	cout<<"���ˣ�������������"<<endl;
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