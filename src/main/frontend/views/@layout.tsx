/* eslint-disable @typescript-eslint/no-explicit-any */
import { Outlet } from 'react-router';
import {
  AppLayout,
  Avatar,
  HorizontalLayout,
  Icon,
  MenuBar,
  MenuBarItemSelectedEvent,
  ProgressBar,
} from '@vaadin/react-components';
import { Suspense } from 'react';

function Header() {
  return (
    <div className="bobby-header flex p-m gap-m items-center" slot="navbar">
      <Icon icon="vaadin:cubes" className="text-primary icon-l" />
      <span className="font-semibold text-l">Bobbysoft</span>
    </div>
  );
}

function NavBar() {
    return (
        <HorizontalLayout
            slot="navbar touch-optimized"
            className="bobby-navbar w-full justify-evenly self-stretch"
        >
            <a
                href="/dashboard"
                aria-label="Dashboard"
                className="flex items-center px-m text-secondary font-medium"
            >
                <Icon icon="vaadin:dashboard" aria-label="Dashboard"/>
                <p>Dashboard</p>
            </a>
            <a
                href="/create"
                aria-label="Create"
                className="flex items-center px-m text-secondary font-medium"
            >
                <Icon icon="vaadin:pencil" aria-label="Create"/>
                <p>Create</p>
            </a>
            <a
                href="/share"
                aria-label="Share"
                className="flex items-center px-m text-secondary font-medium"
            >
                <Icon icon="vaadin:share-square" aria-label="Share"/>
                <p>Share</p>
            </a>
        </HorizontalLayout>
    );
}

function UserMenu() {
  // TODO Replace with real user information and actions
  const items = [
    {
      component: (
        <>
          <Avatar theme="xsmall" name="John Smith" colorIndex={5} className="mr-s" /> John Smith
        </>
      ),
      children: [
        { text: 'View Profile', action: () => console.log('View Profile') },
        { text: 'Manage Settings', action: () => console.log('Manage Settings') },
        { text: 'Logout', action: () => console.log('Logout') },
      ],
    },
  ];
  const onItemSelected = (event: MenuBarItemSelectedEvent) => {
    const action = (event.detail.value as any).action;
    if (action) {
      action();
    }
  };
  return (
    <MenuBar theme="tertiary-inline" items={items} onItemSelected={onItemSelected} className="m-m" slot="navbar" />
  );
}

export default function MainLayout() {
  return (
    <AppLayout primarySection="navbar" className="bobby-app-layout">
      <Header />
      <NavBar />
      <UserMenu />
      <Suspense fallback={<ProgressBar indeterminate={true} className="m-0" />}>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
}

