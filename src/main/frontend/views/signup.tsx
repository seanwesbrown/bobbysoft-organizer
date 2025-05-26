import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { useForm } from '@vaadin/hilla-react-form';
import { useEffect } from 'react';
import { useNavigate } from 'react-router';

import { FormLayout, TextField, Button } from '@vaadin/react-components';
import { PasswordField } from '@vaadin/react-components/PasswordField.js';

import { UserRegistrationService } from 'Frontend/generated/endpoints';
import { getCurrentUser } from 'Frontend/utils/user_utils';

import UserRegistrationDto from 'Frontend/generated/com/bobbysoft/application/usermanagement/dto/UserRegistrationDto';
import UserRegistrationDtoModel from 'Frontend/generated/com/bobbysoft/application/usermanagement/dto/UserRegistrationDtoModel';
import handleError from 'Frontend/views/_ErrorHandler';

export const config: ViewConfig = {
  title: 'Signup',
};

const currentUser = await getCurrentUser();

export default function SignupView() {
  const navigate = useNavigate();
  const signupDisabled = useSignal(false);

  const { model, submit, field, addValidator } = useForm(UserRegistrationDtoModel, {
    onSubmit: async (registration) => {
      try {
        signupDisabled.value = true;
        await UserRegistrationService.registerUser(registration);
        navigate('/');
        window.location.reload();
      } catch (error) {
        handleError(error);
        signupDisabled.value = false;
      }
    },
  });

  const responsiveSteps = [{ minWidth: '0', columns: 1 }];

  useEffect(() => {
    if (currentUser != null) {
      navigate('/');
    }

    addValidator({
      message: 'Please check that the password is repeated correctly',
      validate: (value: UserRegistrationDto) => {
        if (value.password != value.passwordConfirmation) {
          return [{ property: model.passwordConfirmation }];
        }
        return [];
      },
    });
  }, []);

  return (
    <main className="bobby-signup-view w-full h-full flex flex-col box-border gap-s p-m">
      <div className="bobby-signup-form">
        <h2> Sign up </h2>
        <FormLayout responsiveSteps={responsiveSteps}>
          <TextField label="Username" {...field(model.username)} />
          <TextField label="Email" {...field(model.email)} />
          <PasswordField label="Password" {...field(model.password)} />
          <PasswordField label="Confirm Password" {...field(model.passwordConfirmation)} />
        </FormLayout>
        <Button disabled={signupDisabled.value} theme="primary" onClick={submit}>
          Register
        </Button>
      </div>
    </main>
  );
}
