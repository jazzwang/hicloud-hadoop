hicloud-hadoop
==============

This is a toolkit to help developer to install hadoop cluster on hiCloud VMs.

Note: hiCloud is the Amazon EC2 like service provided by CHT, Taiwan.

Requirements
==============

1. These scripts are only tested on Ubuntu 12.04 LTS.
2. You will need the CSV password file download form hiCloud.
3. These scripts are based on libexpect-perl and dsh (distributed shell)

Steps
==============

 * Download PieTTY
   * http://ntu.csie.org/~piaip/pietty/archive/pietty0327.exe
 * Extract passwd.zip, you will need the CSV file
 * Connecting to the First Node you would like to act as NameNode and JobTracker
 * First, please generate SSH key for the First Node (a.k.a. Head Node)


    ~# ssh-keygen -t rsa -P ""


 * Then you have to install needed packages to run this automation script
 

     ~# apt-get install libexpect-perl dsh git


 * Download current version of hicloud-hadoop script from sourceforge


    ~# git clone git://github.com/jazzwang/hicloud-hadoop.git


 * Upload the CSV file using PieTTY

