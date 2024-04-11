using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ServerExemplu
{
    class ClientDataStore
    {
        private List<ClientHandler> _cl = new List<ClientHandler>();
        private static ClientDataStore _instance = new ClientDataStore();
        private ClientDataStore()
        {
        }

        public static ClientDataStore instance
        {
            get
            {
                return _instance;
            }
        }

        public void addClient(ClientHandler cl)
        {
            lock (_cl)
            {
                _cl.Add(cl);
            }
        }

        public void stopClients()
        {
            lock (_cl)
            {
                foreach (ClientHandler cl in _cl)
                    cl.stopClient();
            }
        }

        public int clientCount
        {
            get
            {
                return _cl.Count;
            }
        }
    }
}
