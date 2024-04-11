using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace ServerExemplu
{
    class ServerSocket
    {
        private Socket _sk = null;
        
        protected void createSocket(int port)
        {
            if (null != _sk)
                throw new Exception("Socket already exists!");

            _sk = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp );
            try
            {
                _sk.Bind(new IPEndPoint(IPAddress.Any, port));
                _sk.Listen(10);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        protected Socket acceptConnection()
        {
            try
            {
                return _sk.Accept();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        protected void closeSocket()
        {
            if (null == _sk)
                return;

            _sk.Close();
            _sk = null;
        }
    }
}
