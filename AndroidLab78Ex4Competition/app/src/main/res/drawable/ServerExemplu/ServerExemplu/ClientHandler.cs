using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace ServerExemplu
{
    class ClientHandler
    {
        private Socket _sk = null;
        private int _idx = -1;
        private Thread _th = null;
        private bool _shouldRun = true;
        private bool _isRunning = true;
        public ClientHandler(Socket sk, int id)
        {
            _sk = sk;
            _idx = id;
        }

        public void initClient()
        {
            if (null != _th)
                return;

            _th = new Thread(new ThreadStart(run));
            _th.Start();
        }

        public void stopClient()
        {
            if (_th == null )
                return;

            _sk.Close();
            _shouldRun = false;
        }

        public bool SocketConnected(Socket s)
        {
            bool part1 = s.Poll(10000, SelectMode.SelectRead);
            bool part2 = (s.Available == 0);
            if (part1 && part2)
                return false;
            else
                return true;
        }
        private void handleMsg(String msg)
        {
                                 
            //prelucrare msg.....
            char[] sep = {'-'};
            String[] arrMsg = msg.Split(sep);
            if (arrMsg[0].StartsWith(Mesaje.sLoginReq)){
                //avem cerere tip login
                //acces tabel utilizatori
                //verificat daca exista utilizator arrMsg[1]
                //varificat parola arrMsg[2]...
                Console.WriteLine(arrMsg[1]);
                if (arrMsg[1].StartsWith("Radu"))
                {

                    sendResponseLogin(Mesaje.sLoginOK);
                }
                
            }
            else if (arrMsg[0].StartsWith("logout"))
            {
                //avem cerere tip logout
                
            }
            else if (arrMsg[0].StartsWith("sendMsg"))
            {
                //avem cerere de transmitere mesag catre destinatar
            }
            else
            {
                //avem cerere eronata
               // sendResponse("eroare-123");
            }

            

        }
        private void sendResponseLogin(String msg)
        {
            //procesare si trimitere raspuns in cazul mesajului Login
            //verificam daca exista user-ul...
            String raspuns = "";
            //if user exists?
            raspuns = raspuns.Insert(0, "login");
            raspuns = raspuns.Insert(4, "ok");
            byte[] bytesMsgRaspuns = Encoding.ASCII.GetBytes(raspuns);
            _sk.Send(bytesMsgRaspuns);
        }
        
        private void run()
        {
            // Attention! This is the largest message one can receive!
            

            while (_shouldRun)
            {
                //Console.WriteLine("Client... "+_idx);
                byte[] rawMsg = new byte[10];
                try
                {
                    
                        int bCount = _sk.Receive(rawMsg);
                        String msg = Encoding.UTF8.GetString(rawMsg);
                        if (bCount > 0)
                            Console.WriteLine("Client " + _idx + ": " + msg);
                        handleMsg(msg);
                                     
                        
                }
                catch (Exception ex)
                {
                    //Console.WriteLine("Client exxxccp ");
                    return;
                }
                Thread.Sleep(1);
            }
            _isRunning = false;
            
        }

        public bool isAlive()
        {
            return _isRunning;
        }
    }
}
