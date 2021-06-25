1) create a new repo on github

git clone git@github.com:RobertBerger/meta-polarfire-soc-yocto-bsp-aries.git

2) add my-scripts dir

cd meta-polarfire-soc-yocto-bsp-aries

echo "# meta-polarfire-soc-yocto-bsp fork" >> README.md

git add .

git commit -m "first commit"

git push -u origin master

3) backup my repo, so I can edit this file

cp -r meta-riscv mets-riscv.ori

4) add upstream

cd meta-riscv

git remote add official-upstream git://github.com/riscv/meta-riscv.git

git fetch official-upstream

warning: no common commits
remote: Enumerating objects: 2617, done.
remote: Counting objects: 100% (351/351), done.
remote: Compressing objects: 100% (174/174), done.
remote: Total 2617 (delta 180), reused 297 (delta 155), pack-reused 2266
Receiving objects: 100% (2617/2617), 7.23 MiB | 4.02 MiB/s, done.
Resolving deltas: 100% (1152/1152), done.
From git://github.com/riscv/meta-riscv
 * [new branch]      dunfell    -> official-upstream/dunfell
 * [new branch]      gatesgarth -> official-upstream/gatesgarth
 * [new branch]      hardknott  -> official-upstream/hardknott
 * [new branch]      master     -> official-upstream/master
 * [new branch]      sumo       -> official-upstream/sumo

git branch -a

* master
  remotes/official-upstream/dunfell
  remotes/official-upstream/gatesgarth
  remotes/official-upstream/hardknott
  remotes/official-upstream/master
  remotes/official-upstream/sumo
  remotes/origin/HEAD -> origin/master
  remotes/origin/master

5) use specific upstream branch/commit and make own branch

syntax: git fetch url-to-repo branchname:refs/remotes/origin/branchname

git fetch git://github.com/riscv/meta-riscv.git hardknott:refs/remotes/origin/hardknott

From git://github.com/riscv/meta-riscv
 * [new branch]      hardknott  -> origin/hardknott

git branch -a
* master
  remotes/official-upstream/dunfell
  remotes/official-upstream/gatesgarth
  remotes/official-upstream/hardknott
  remotes/official-upstream/master
  remotes/official-upstream/sumo
  remotes/origin/HEAD -> origin/master
  remotes/origin/hardknott
  remotes/origin/master

6) Update from upstream:
git co master
>> git remote -v

official-upstream       git://github.com/riscv/meta-riscv.git (fetch)
official-upstream       git://github.com/riscv/meta-riscv.git (push)
origin  git@github.com:RobertBerger/meta-riscv.git (fetch)
origin  git@github.com:RobertBerger/meta-riscv.git (push)

>> git fetch official-upstream

---

7) My own branch:

git co master
git co official-upstream/hardknott
git checkout -b 2021-06-25-hardknott
git co master
cd my-scripts
./push-all-to-github.sh

8) apply patches

stg init

stg series

stg import --mail ../meta-virtualization-patches/2019-09-09-warrior-2.7+/0001-use-systemd-as-cgroup-manager-for-docker-While-it-s-.patch

import all patches

...

stg series

stg commit --all

git log

git co master

9) push to my upstream

my-scripts/push-all-to-github.sh

