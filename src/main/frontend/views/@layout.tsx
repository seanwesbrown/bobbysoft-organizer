/* eslint-disable @typescript-eslint/no-explicit-any */
import { Outlet, useNavigate } from 'react-router';
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
import { useAuth } from 'Frontend/security/auth';
import { getCurrentUser } from 'Frontend/utils/user_utils';
import UserInfoDto from 'Frontend/generated/com/bobbysoft/application/usermanagement/dto/UserInfoDto';

const currentUser = await getCurrentUser();

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
    <HorizontalLayout slot="navbar touch-optimized" className="bobby-navbar w-full justify-evenly self-stretch">
      <a href="/organizer" aria-label="My Organizer" className="flex items-center px-m text-secondary font-medium">
        <Icon icon="vaadin:dashboard" aria-label="My Organizer" />
        <p>My Organizer</p>
      </a>
      <a href="/create" aria-label="Create" className="flex items-center px-m text-secondary font-medium">
        <Icon icon="vaadin:pencil" aria-label="Create" />
        <p>Create</p>
      </a>
      <a href="/share" aria-label="Share" className="flex items-center px-m text-secondary font-medium">
        <Icon icon="vaadin:share-square" aria-label="Share" />
        <p>Share</p>
      </a>
    </HorizontalLayout>
  );
}

function UserMenu() {
  let items;

  if (currentUser != null) {
    items = userMenuItems(currentUser);
  } else {
    items = loginMenuItems();
  }

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

function userMenuItems(user: UserInfoDto) {
  const { logout } = useAuth();

  return [
    {
      component: (
        <>
          <Avatar theme="small" name={user.name} colorIndex={5} className="mr-s" />
        </>
      ),
      children: [
        { text: 'View Profile', action: () => console.log('View Profile') },
        { text: 'Manage Settings', action: () => console.log('Manage Settings') },
        { text: 'Logout', action: () => logout() },
      ],
    },
  ];
}

function loginMenuItems() {
  const navigate = useNavigate();

  return [
    {
      component: (
        <>
          <Avatar theme="small" className="mr-s" />
        </>
      ),
      children: [
        { text: 'Login', action: () => navigate('/login') },
        { text: 'Sign Up', action: () => navigate('/signup') },
      ],
    },
  ];
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
