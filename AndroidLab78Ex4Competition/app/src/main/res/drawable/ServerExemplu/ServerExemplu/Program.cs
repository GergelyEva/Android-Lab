using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Runtime.InteropServices;

namespace ServerExemplu
{

    class Program
    {
        private static bool _exitKeyPressed = false;
        static void Main(string[] args)
        {
            Console.Clear();
            ServerCore sv = new ServerCore();
            try
            {
                sv.createServer(8000);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            
            // Set-up the event handler to stop the server
            Console.CancelKeyPress += new ConsoleCancelEventHandler(keyHandler);

            Console.WriteLine("Server is running!");
            Console.WriteLine("Press CTRL+C to stop the server!");

            while (!_exitKeyPressed)
            {
                Thread.Sleep(100);

                // TODO: perform user handler cleanup ..
            }

            Console.WriteLine("Running cleanup ...");
            Console.WriteLine("Stopping server ...");
            sv.stopServer();
            Console.WriteLine("Cleaning up clients ...");
            ClientDataStore.instance.stopClients();
            Console.WriteLine("Server is down!");
        }

        protected static void keyHandler(object sender, ConsoleCancelEventArgs args)
        {
            Console.WriteLine("The server was interrupted!");
            args.Cancel = true;
            if (args.SpecialKey == ConsoleSpecialKey.ControlC)
                _exitKeyPressed = true;
        }
    }
}
