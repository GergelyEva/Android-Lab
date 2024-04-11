using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Net;
using System.Net.Sockets;

namespace ServerExemplu
{
    class ServerCore : ServerSocket
    {
        private Thread _th = null;
        private bool _shouldRun = true;
        public void createServer(int port)
        {
            createSocket(port);
            _th = new Thread(new ThreadStart(run));
            _th.Start();
        }
        public void stopServer()
        {
            closeSocket();
        }

        private void run()
        {
            while (_shouldRun)
            {
                try
                {
                    Socket sk = acceptConnection();
                    if (sk == null)
                        return;

                    Console.WriteLine("Accepted connection from: " + sk.RemoteEndPoint);
                    ClientHandler cl = new ClientHandler(sk, ClientDataStore.instance.clientCount);
                    cl.initClient();
                    ClientDataStore.instance.addClient(cl);
                }
                catch (Exception ex)
                {
                    return;
                }

                Thread.Yield();
            }
        }
    }
}
